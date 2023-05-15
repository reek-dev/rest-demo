package com.example.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotATeacherException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotATeacherException(Long id) {
        super(String.format("The user with id `%d` is not a teacher", id));
    }
}
