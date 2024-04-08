package com.driver.SwiggatoApplication.exception;

public class MenuItemNotFoundException extends RuntimeException{
    public MenuItemNotFoundException(String message){
        super(message);
    }
}
