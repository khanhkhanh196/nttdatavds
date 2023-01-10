package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductErrorResponse {
	private int status;
	private String message;
	private long timeStamp;
}
