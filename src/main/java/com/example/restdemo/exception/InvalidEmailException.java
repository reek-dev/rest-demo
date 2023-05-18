package com.example.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidEmailException(String email) {
        super(String.format("The email `%s` is not valid.", email));
    }
}
