package com.example.demo.restapi;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.converter.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;

@RestController
@RequestMapping("/rest")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DTOConverter converter;

	@PostMapping("/categories")
	public void addNewCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
//		category.setCategoryId(0);
		Category category = converter.convertCategoryDtoToEntity(categoryDTO);
		categoryService.saveCategory(category);
	}

	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
	}
}
