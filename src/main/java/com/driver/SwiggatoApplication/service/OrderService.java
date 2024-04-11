package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.responseDto.OrderResponse;
import com.driver.SwiggatoApplication.exception.CustomerNotFoundException;
import com.driver.SwiggatoApplication.exception.EmptyCartException;
import com.driver.SwiggatoApplication.model.*;
import com.driver.SwiggatoApplication.repository.CustomerRepository;
import com.driver.SwiggatoApplication.repository.DeliveryPartnerRepo;
import com.driver.SwiggatoApplication.repository.OrderEntityRepo;
import com.driver.SwiggatoApplication.repository.RestaurantRepository;
import com.driver.SwiggatoApplication.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    final CustomerRepository customerRepository;
    final DeliveryPartnerRepo deliveryPartnerRepo;
    final OrderEntityRepo orderEntityRepo;
    final RestaurantRepository restaurantRepository;

//    @Autowired
//    JavaMailSender javaMailSender;

    public OrderService(CustomerRepository customerRepository, DeliveryPartnerRepo deliveryPartnerRepo, OrderEntityRepo orderEntityRepo, RestaurantRepository restaurantRepository) {
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepo = deliveryPartnerRepo;
        this.orderEntityRepo = orderEntityRepo;
        this.restaurantRepository = restaurantRepository;
    }

    public OrderResponse placeOrder(String customerMobile) {
        // verify the customer
        Customer customer = customerRepository.findByMobileNo(customerMobile);
        if(customer == null){
            throw new CustomerNotFoundException("Invalid mobile number!!!");
        }
        // verify if cart is empty or not
        Cart cart = customer.getCart();
        if(cart.getFoodItems().size()==0){
            throw new EmptyCartException("Sorry! Your cart is empty!!!");
        }
        // find a delivery partner to deliver. Randomly
        DeliveryPartner partner = deliveryPartnerRepo.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenuItem().getRestaurant();

        // prepare the order entity
        OrderEntity order = OrderTransformer.prepareOrderEntity(cart);

        OrderEntity savedOrder = orderEntityRepo.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(partner);
        order.setRestaurant(restaurant);
        order.setFoodItems(cart.getFoodItems());

        customer.getOrders().add(savedOrder);
        partner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        for(FoodItem foodItem: cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }
        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepo.save(partner);
        restaurantRepository.save(restaurant);


//          send an email
//        String text = "Hi! " + customer.getName() + " The below order is ready\n" +
//                order.getOrderId() + " \nThis is the Food list: "+order.getFoodItems();
//
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("patelrambharat@gamil.com");
//        simpleMailMessage.setTo(customer.getEmail());
//        simpleMailMessage.setSubject("Congrats!! Order Delivered");
//        simpleMailMessage.setText(text);
//
//
//
//        javaMailSender.send(simpleMailMessage);

        // prepare orderresponse
        return OrderTransformer.OrderToOrderResponse(savedOrder);
    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}

