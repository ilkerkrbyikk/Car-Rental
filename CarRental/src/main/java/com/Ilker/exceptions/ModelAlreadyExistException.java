package com.Ilker.exceptions;

public class ModelAlreadyExistException extends RuntimeException{

    public ModelAlreadyExistException(String message){
        super(message);
    }
}
