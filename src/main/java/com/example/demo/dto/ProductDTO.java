package com.example.demo.dto;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Category;
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
	private List<Category> categorySet;
	private List<File> files;
}
