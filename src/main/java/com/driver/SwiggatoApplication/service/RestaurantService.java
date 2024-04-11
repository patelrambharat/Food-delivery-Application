package com.driver.SwiggatoApplication.service;

import com.driver.SwiggatoApplication.dto.requestDto.MenuRequest;
import com.driver.SwiggatoApplication.dto.requestDto.RestaurantRequest;
import com.driver.SwiggatoApplication.dto.responseDto.RestaurantResponse;
import com.driver.SwiggatoApplication.exception.RestaurantNotFoundException;
import com.driver.SwiggatoApplication.model.MenuItem;
import com.driver.SwiggatoApplication.model.Restaurant;
import com.driver.SwiggatoApplication.repository.RestaurantRepository;
import com.driver.SwiggatoApplication.transformer.MenuItemTransformer;
import com.driver.SwiggatoApplication.transformer.RestaurantTransformer;
import com.driver.SwiggatoApplication.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    final ValidationUtils validationUtils;

    public RestaurantService(RestaurantRepository restaurantRespository,
                             ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRespository;
        this.validationUtils = validationUtils;
    }
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {
        // dto -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        //persist/save the model in db
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // prepare response dto from model
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public String changeOpenedStatus(int id) {
//        Optional<Restaurant> optional = restaurantRepository.findById(id);
//        if(optional.isEmpty()){
//            throw  new RestaurantNotFoundException("Restaurant doesn't exist");
//        }
//
//        Restaurant restaurant = optional.get();
//        restaurant.setOpened(!restaurant.isOpened());
//        Restaurant savedRestaurant =  restaurantRepository.save(restaurant);
//
//        if(savedRestaurant.isOpened()){
//            return "Restaurant is Opened Now";
//        }
//        return "Restaurant is Closed";

        //check whether id is valid or not
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());
        restaurantRepository.save(restaurant);

        if(restaurant.isOpened()){
            return "Restaurant is opened now!!!";
        }

        return "Restaurant is closed";
    }

    public RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest, int id) {
//        FoodItem foodItem = FoodItemTransformer.FoodRequestToFoodItem(foodRequest);
//        // check restaurant valid or not
//        Optional<Restaurant> optional = restaurantRepository.findById(id);
//        if(optional.isEmpty()){
//            throw new RestaurantNotFoundException("!!Restaurant doesn't exist!!");
//        }
//
//        //get restaurant
//        Restaurant restaurant = optional.get();
//        foodItem.setRestaurant(restaurant);
//
//        // add food item to restaurant
//        restaurant.getAvailableFoodItems().add(foodItem);
//
//        // saved both restaurant and food Item
//        Restaurant saved = restaurantRepository.save(restaurant);
//        return RestaurantTransformer.RestaurantToRestaurantResponse(saved);

        // check reataurant is valid or not
        if(!validationUtils.validateRestaurantId(menuRequest.getRestaurantId())){
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestaurantId()).get();
        // make food entity
        MenuItem menuItem = MenuItemTransformer.FoodRequestToFoodItem(menuRequest);
        menuItem.setRestaurant(restaurant);

        restaurant.getAvailableMenuItems().add(menuItem);

        // save rest and food
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // prepare response
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }
}

