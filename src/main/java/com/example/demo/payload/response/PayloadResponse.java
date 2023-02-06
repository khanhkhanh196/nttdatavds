package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayloadResponse <E>{
    private int status;
    private String message;
    private long timeStamp;
    E body;
}
