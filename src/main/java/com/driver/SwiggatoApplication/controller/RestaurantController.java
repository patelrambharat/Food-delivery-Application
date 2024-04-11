package com.driver.SwiggatoApplication.controller;

import com.driver.SwiggatoApplication.dto.requestDto.MenuRequest;
import com.driver.SwiggatoApplication.dto.requestDto.RestaurantRequest;
import com.driver.SwiggatoApplication.dto.responseDto.RestaurantResponse;
import com.driver.SwiggatoApplication.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/status/change")
    public ResponseEntity changeOpenedStatus(@RequestParam("id") int id){
        String msg = restaurantService.changeOpenedStatus(id);
        return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-menu-to-restaurant")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest, @RequestParam("restaurant-id") int id ){
        try {
            RestaurantResponse restaurantResponse = restaurantService.addMenuItemToRestaurant(menuRequest, id);
            return new ResponseEntity<>(restaurantResponse, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        // get menu of a restaurant
        // give all the restauratns who have served more than 'x' number of orders

        // give the restaurants which have maximum number of items in their menu and which are opened also
    }
}


