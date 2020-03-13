package com.example.birthdayapp.exception;

public class NotFoundGuestException extends RuntimeException {

    public NotFoundGuestException(String s) {
        super(s);
    }
}
