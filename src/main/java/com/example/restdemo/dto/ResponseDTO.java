package com.example.restdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {

    private String status;
    private Integer statusCode;
    private String message;
    private T data;
}
