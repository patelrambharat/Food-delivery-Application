package com.driver.SwiggatoApplication.exception;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String msg){
        super(msg);
    }
}
