package com.example.demo.restapi;

import com.example.demo.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/categories")
	public ResponseEntity addNewCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
//		category.setCategoryId(0);
		Category category = categoryDTO.convertToEntity();
		categoryService.saveCategory(category);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		CategoryDTO dto = category.convertToCategoryDTO();
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> findCategoryByName(@RequestParam("name") String name) {
		List<Category> categories = categoryService.getCategoryByName(name);
		List<CategoryDTO> categoryDTOS = new ArrayList<>();
		for (Category category: categories) {
			categoryDTOS.add(category.convertToCategoryDTO());
		}
		return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
	}
}
