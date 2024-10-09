package com.Ilker.exceptions;

public class CarIsNotAvailableException extends RuntimeException{

    public CarIsNotAvailableException(String message){
        super(message);
    }
}
