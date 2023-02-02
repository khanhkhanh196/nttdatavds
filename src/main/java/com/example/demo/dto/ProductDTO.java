package com.example.demo.dto;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Category;
import com.example.demo.entity.File;

import com.example.demo.entity.Product;
import com.example.demo.service.CategoryServiceImpl;
import com.example.demo.service.FileServiceImpl;
import com.example.demo.service.serviceinterface.CategoryService;
import com.example.demo.service.serviceinterface.FileService;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
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
