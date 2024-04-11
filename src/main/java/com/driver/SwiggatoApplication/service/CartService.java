package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.requestDto.FoodRequest;
import com.driver.SwiggatoApplication.dto.responseDto.CartStatusResponse;
import com.driver.SwiggatoApplication.dto.responseDto.FoodResponse;
import com.driver.SwiggatoApplication.exception.CustomerNotFoundException;
import com.driver.SwiggatoApplication.exception.MenuItemNotFoundException;
import com.driver.SwiggatoApplication.model.*;
import com.driver.SwiggatoApplication.repository.CartRepository;
import com.driver.SwiggatoApplication.repository.CustomerRepository;
import com.driver.SwiggatoApplication.repository.FoodRepo;
import com.driver.SwiggatoApplication.repository.MenuRepository;
import com.driver.SwiggatoApplication.transformer.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    final CustomerRepository customerRepository;
    final MenuRepository menuRepository;
    final FoodRepo foodRepo;
    final CartRepository cartRepository;


    public CartService(CustomerRepository customerRepository, MenuRepository menuRepository, FoodRepo foodRepo, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.foodRepo = foodRepo;
        this.cartRepository = cartRepository;
    }

    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest)
    {
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if (customer == null)
        {
            throw new CustomerNotFoundException("Customer doesn't exisit");
        }

        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodRequest.getMenuItemId());

        if (menuItemOptional.isEmpty()) {
            throw new MenuItemNotFoundException("Item not available in the restaurant!!!");
        }





        MenuItem menuItem = menuItemOptional.get();
        if (!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable())
        {
            throw new MenuItemNotFoundException("Given dish is out of stock for now!!!");
        }
        Cart cart = customer.getCart();

        // having item from same restaurant
        if(cart.getFoodItems().size()!=0){
            Restaurant currRestaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();
            Restaurant newRestaurant = menuItem.getRestaurant();

            if(!currRestaurant.equals(newRestaurant)){
                List<FoodItem> foodItems = cart.getFoodItems();
                for(FoodItem foodItem: foodItems) {
                    foodItem.setCart(null);
                    foodItem.setOrder(null);
                    foodItem.setMenuItem(null);
                }
                cart.setCartTotal(0);
                cart.getFoodItems().clear();
                foodRepo.deleteAll(foodItems);
            }
        }

        boolean alreadyExists = false;
        FoodItem savedFoodItem = null;
        if(cart.getFoodItems().size()!=0){
            for(FoodItem foodItem: cart.getFoodItems()){
                if(foodItem.getMenuItem().getId()==menuItem.getId()){
                    savedFoodItem = foodItem;
                    int curr = foodItem.getRequiredQuantity();
                    foodItem.setRequiredQuantity(curr+foodRequest.getRequiredQuantity());
                    alreadyExists = true;
                    break;
                }
            }
        }

        if (!alreadyExists) {

            // ready to add item to cart
            FoodItem foodItem = FoodItem.builder()
                    .menuItem(menuItem)
                    .requiredQuantity(foodRequest.getRequiredQuantity())
                    .cart(customer.getCart())
                    .totalCost(foodRequest.getRequiredQuantity() * menuItem.getPrice())
                    .build();

                savedFoodItem = foodRepo.save(foodItem);
                cart.getFoodItems().add(savedFoodItem);
                menuItem.getFoodItems().add(savedFoodItem);
                savedFoodItem.setCart(cart);

                 }

            int cartTotal = 0;
            for (FoodItem food : cart.getFoodItems()) {
                cartTotal += food.getRequiredQuantity() * food.getMenuItem().getPrice();
            }
            cart.getFoodItems().add(savedFoodItem);
            cart.setCartTotal(cartTotal);


            // save
            Cart savedCart = cartRepository.save(cart);
            MenuItem savedMenuItem = menuRepository.save(menuItem);



            // prepare
            List<FoodResponse> foodResponseList = new ArrayList<>();
            for (FoodItem food : cart.getFoodItems()) {
                foodResponseList.add(FoodTransformer.FoodToFoodResponse(food));
            }

            return CartStatusResponse.builder()
                    .customerName(savedCart.getCustomer().getName())
                    .customerMobile(savedCart.getCustomer().getMobileNo())
                    .customerAddress(savedCart.getCustomer().getAddress())
                    .foodList(foodResponseList)
                    .restaurantName(savedMenuItem.getRestaurant().getName())
                    .cartTotal(cartTotal)
                    .build();


        }
    }
