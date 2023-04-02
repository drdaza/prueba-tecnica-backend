package com.drdaza.app.exceptions;

public class PurchaseNotFounException extends RuntimeException{
    public PurchaseNotFounException(String message){
        super(message);
    }
}
