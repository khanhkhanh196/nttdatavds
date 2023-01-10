package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Review;

import lombok.Data;

@Data
public class ProductDTO {
	private int id;
	private String productName;
	private String productDescription;
	private int stock;
	private int soldQuantity;
	private int brand;
	private List<Review> reviews;
}
