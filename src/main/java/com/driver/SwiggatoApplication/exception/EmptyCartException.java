package com.driver.SwiggatoApplication.exception;

public class EmptyCartException extends RuntimeException{

    public EmptyCartException(String message){
        super(message);
    }
}
