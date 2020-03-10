package com.example.birthdayapp.exception;

public class NotFoundRoomException extends RuntimeException {

    public NotFoundRoomException(String message){
        super(message);
    }
}
