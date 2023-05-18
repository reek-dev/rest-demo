package com.example.restdemo.exception;

import java.io.Serial;

public class InvalidNameException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidNameException(String name) {
        super(String.format("The name `%s` is not valid, name must only contain alphabets", name));
    }
}
