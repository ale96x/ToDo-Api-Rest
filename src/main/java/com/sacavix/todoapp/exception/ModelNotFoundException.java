package com.sacavix.todoapp.exception;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(String message){
        super(message);
    }
}
