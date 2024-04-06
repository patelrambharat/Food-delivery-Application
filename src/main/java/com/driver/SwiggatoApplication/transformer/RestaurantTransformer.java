package com.driver.SwiggatoApplication.transformer;

import com.driver.SwiggatoApplication.dto.requestDto.RestaurantRequest;
import com.driver.SwiggatoApplication.dto.responseDto.FoodResponse;
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
                .availableFoodItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .opened(true)
                .build();
    }
    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant) {

        List<FoodResponse> menu = restaurant.getAvailableFoodItems()
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
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
