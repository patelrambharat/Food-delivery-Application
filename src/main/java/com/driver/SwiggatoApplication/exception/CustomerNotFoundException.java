package com.driver.SwiggatoApplication.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
