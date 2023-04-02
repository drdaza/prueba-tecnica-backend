package com.drdaza.app.exceptions;

public class PayMethodNotFound extends RuntimeException{
    public PayMethodNotFound(String message){
        super(message);
    }
}
