package com.example.demo.restapi;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.converter.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity addNewCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
//		category.setCategoryId(0);
		Category category = converter.convertCategoryDtoToEntity(categoryDTO);
		categoryService.saveCategory(category);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		CategoryDTO dto = converter.convertToCategoryDTO(category);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/categories")
	public ResponseEntity<CategoryDTO> findCategoryByName(@RequestParam("name") String name) {
		Category category = categoryService.getCategoryByName(name);
		CategoryDTO dto = converter.convertToCategoryDTO(category);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
	}
}
