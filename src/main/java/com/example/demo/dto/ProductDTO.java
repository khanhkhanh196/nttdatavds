package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.File;

import lombok.Data;

@Data
public class ProductDTO {
	private int id;
	private String name;
	private String shortDesc;
	private double price;
	private int stock;
	private int sold;
	private List<File> files;
}
