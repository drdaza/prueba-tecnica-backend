package com.drdaza.app.exceptions;

public class ProfileNotFounException extends RuntimeException{
    public ProfileNotFounException(String message) {
        super(message);
    }
}
