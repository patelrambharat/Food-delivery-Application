package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.requestDto.RestaurantRequest;
import com.driver.SwiggatoApplication.dto.responseDto.MenuResponse;
import com.driver.SwiggatoApplication.dto.responseDto.RestaurantResponse;
import com.driver.SwiggatoApplication.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {
    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .contactNumber(restaurantRequest.getContactNumber())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .availableMenuItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .opened(true)
                .build();
    }
    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant) {

        List<MenuResponse> menu = restaurant.getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
         return RestaurantResponse.builder()
                .name(restaurant.getName())
                .location((restaurant.getLocation()))
                .contactNumber(restaurant.getContactNumber())
                .opened(restaurant.isOpened())
                .menu(menu)
                .build();
    }
}
