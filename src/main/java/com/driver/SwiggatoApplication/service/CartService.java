package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.requestDto.FoodRequest;
import com.driver.SwiggatoApplication.dto.responseDto.CartStatusResponse;
import com.driver.SwiggatoApplication.dto.responseDto.FoodResponse;
import com.driver.SwiggatoApplication.exception.CustomerNotFoundException;
import com.driver.SwiggatoApplication.exception.MenuItemNotFoundException;
import com.driver.SwiggatoApplication.model.Cart;
import com.driver.SwiggatoApplication.model.Customer;
import com.driver.SwiggatoApplication.model.FoodItem;
import com.driver.SwiggatoApplication.model.MenuItem;
import com.driver.SwiggatoApplication.repository.CartRepository;
import com.driver.SwiggatoApplication.repository.CustomerRepository;
import com.driver.SwiggatoApplication.repository.FoodRepo;
import com.driver.SwiggatoApplication.repository.MenuRepository;
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

    public CartStatusResponse addFoodItemToCart(FoodRequest foodRequest) {
        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exisit");
        }

        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodRequest.getMenuItemId());
        if(menuItemOptional.isEmpty()){
            throw new MenuItemNotFoundException("Item not available in the restaurant!!!");
        }

        MenuItem menuItem = menuItemOptional.get();
        if(!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable()){
            throw new MenuItemNotFoundException("Given dish is out of stock for now!!!");
        }

        // ready to add item to cart
        FoodItem foodItem = FoodItem.builder()
                .menuItem(menuItem)
                .cart(customer.getCart())
                .requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost(foodRequest.getRequiredQuantity()*menuItem.getPrice())
                .build();

        Cart cart = customer.getCart();
        FoodItem savedFoodItem = foodRepo.save(foodItem);

        double cartTotal = 0;
        for(FoodItem food: cart.getFoodItems()){
            cartTotal += food.getRequiredQuantity()*food.getMenuItem().getPrice();
        }
        cart.getFoodItems().add(savedFoodItem);
        cart.setCartTotal(cartTotal);
        menuItem.getFoodItems().add(savedFoodItem);

        // save
        Cart savedCart = cartRepository.save(cart);
        MenuItem savedMenuItem = menuRepository.save(menuItem);

        // prepare
        List<FoodResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food: cart.getFoodItems()){
            FoodResponse foodResponse = FoodResponse.builder()
                    .dishName(food.getMenuItem().getDishName())
                    .price(food.getMenuItem().getPrice())
                    .category(food.getMenuItem().getCategory())
                    .veg(food.getMenuItem().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
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
