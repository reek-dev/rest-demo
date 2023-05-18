package com.example.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetail> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "RESOURCE_NOT_FOUND"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetail);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "RESOURCE_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "USER_EMAIL_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionDetail> handleInvalidEmailException(
            InvalidEmailException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "EMAIL_NOT_VALID"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ExceptionDetail> handleInvalidPhoneException(
            InvalidPhoneException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "PHONE_NOT_VALID"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ExceptionDetail> handleInvalidNameException(
            InvalidNameException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "NAME_NOT_VALID"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handlePhoneNumberAlreadyExistsException(
            PhoneNumberAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "PHONE_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(StateAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handleStateAlreadyExistsException(
            StateAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "STATE_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handleCityAlreadyExistsException(
            CityAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "CITY_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(NotATeacherException.class)
    public ResponseEntity<ExceptionDetail> handleNotATeacherException(
            NotATeacherException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "NOT_A_TEACHER"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handleCourseAlreadyExistsException(
            CourseAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "COURSE_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(CourseCategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionDetail> handleCourseCategoryAlreadyExistsException(
            CourseCategoryAlreadyExistsException exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "COURSE_CATEGORY_ALREADY_EXISTS"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetail> handleGlobalException(
            Exception exception,
            WebRequest request
    ) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDetail);
    }
}
