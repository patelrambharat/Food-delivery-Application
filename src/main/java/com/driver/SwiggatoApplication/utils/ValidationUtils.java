package com.driver.SwiggatoApplication.utils;

import com.driver.SwiggatoApplication.repository.CustomerRepository;
import com.driver.SwiggatoApplication.repository.RestaurantRepository;
import com.driver.SwiggatoApplication.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {
    final RestaurantRepository restaurantRepository; //constructor injection

    @Autowired
    public ValidationUtils(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean validateRestaurantId(int id){

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        return restaurantOptional.isPresent();
    }

}
