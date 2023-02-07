package com.example.demo.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int productId;
	private String productName;
	private String shortDesc;
	private double price;
	private int stock;
	private int sold;
	private List<Integer> categoriesIds;
	private List<Integer> filesIds;

}
