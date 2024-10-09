package com.Ilker.exceptions;

public class ReviewAlreadyExistException extends RuntimeException{

    public ReviewAlreadyExistException(String message){
        super(message);
    }
}
