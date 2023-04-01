package com.drdaza.app.exceptions;

public class UserNotFounException extends RuntimeException{
    public UserNotFounException(String message){
        super(message);
    }
}
