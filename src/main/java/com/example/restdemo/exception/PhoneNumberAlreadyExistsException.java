package com.example.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhoneNumberAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PhoneNumberAlreadyExistsException(String phone) {
        super(String.format("The phone number `%s` already exists in the database.", phone));
    }
}
