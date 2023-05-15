package com.example.restdemo.exception;

import com.example.restdemo.entity.Course;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourseCategoryAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CourseCategoryAlreadyExistsException(String category) {
        super(String.format("The category `%s` already exists in the database.", category));
    }
}
