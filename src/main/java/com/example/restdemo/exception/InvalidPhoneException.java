package com.example.restdemo.exception;

import java.io.Serial;

public class InvalidPhoneException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPhoneException(String phone) {
        super(String.format("The phone `%s` is not valid, it should be of 10 digit.", phone));
    }
}
