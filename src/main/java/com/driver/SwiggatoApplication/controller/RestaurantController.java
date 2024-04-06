package com.driver.SwiggatoApplication.controller;

import com.driver.SwiggatoApplication.dto.requestDto.RestaurantRequest;
import com.driver.SwiggatoApplication.dto.responseDto.RestaurantResponse;
import com.driver.SwiggatoApplication.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    /*
  Constructor Injection not field injection
  @param restaurantService --> bean of restaurant Service
   */
    final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }
    @PostMapping("/add-restaurant")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        try {
            RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
            return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Restaurant already exist",HttpStatus.BAD_REQUEST);
        }
    }
}
