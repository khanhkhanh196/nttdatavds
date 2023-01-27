package com.example.demo.dto;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Category;
import com.example.demo.entity.File;

import com.example.demo.entity.Product;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ProductDTO {
	private int id;
	private String name;
	private String shortDesc;
	private double price;
	private int stock;
	private int sold;
	private List<Category> categoriesSet;
	private List<File> files;

	public Product convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		Product product = modelMapper.map(this, Product.class);
		return product;
	}

}
