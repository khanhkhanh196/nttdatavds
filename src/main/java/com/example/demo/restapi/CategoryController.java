package com.example.demo.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;

@RestController
@RequestMapping("/rest")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/categories")
	public void addNewProduct(@Validated @RequestBody Category category) {
		category.setCategoryId(0);
		categoryService.saveCategory(category);
	}
}
