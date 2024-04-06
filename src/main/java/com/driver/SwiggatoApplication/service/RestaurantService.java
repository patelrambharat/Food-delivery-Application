package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.requestDto.RestaurantRequest;
import com.driver.SwiggatoApplication.dto.responseDto.RestaurantResponse;
import com.driver.SwiggatoApplication.model.Restaurant;
import com.driver.SwiggatoApplication.repository.RestaurantRepository;
import com.driver.SwiggatoApplication.transformer.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        // dto -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        //persist/save the model in db
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // prepare response dto from model
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }
}
