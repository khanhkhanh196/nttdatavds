package com.example.demo.dto;

import lombok.Value;

@Value
public class ApiResponse<T> {
    private String status;
    private T data;
    private String message;
}
