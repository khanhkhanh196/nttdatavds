package com.example.demo.restapi;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.payload.response.PayloadResponse;
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
	public ResponseEntity<PayloadResponse<CategoryDTO>> addNewCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
		Category category = categoryDTO.convertToEntity();
		categoryService.saveCategory(category);
		PayloadResponse<CategoryDTO> payloadResponse = new PayloadResponse<>(201, "Category created", System.currentTimeMillis(), categoryDTO);
		return new ResponseEntity<>(payloadResponse, HttpStatus.CREATED);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<PayloadResponse<CategoryDTO>> findCategoryById(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		CategoryDTO dto = category.convertToCategoryDTO();
		PayloadResponse<CategoryDTO> payloadResponse = new PayloadResponse<>(200, "Category found", System.currentTimeMillis(), dto);
		return new ResponseEntity<>(payloadResponse, HttpStatus.OK);
	}

	@GetMapping("/categories")
	public ResponseEntity<PayloadResponse<List<CategoryDTO>>> findCategoryByName(@RequestParam("name") String name) {
		List<Category> categories = categoryService.getCategoryByName(name);
		List<CategoryDTO> categoryDTOS = new ArrayList<>();
		for (Category category: categories) {
			categoryDTOS.add(category.convertToCategoryDTO());
		}
		PayloadResponse<List<CategoryDTO>> payloadResponse = new PayloadResponse<>(200, "Category found", System.currentTimeMillis(), categoryDTOS);
		return new ResponseEntity<>(payloadResponse, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<PayloadResponse<CategoryDTO>> deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		PayloadResponse<CategoryDTO> payloadResponse = new PayloadResponse<>(200, "Category deleted", System.currentTimeMillis());
		return new ResponseEntity<>(payloadResponse, HttpStatus.OK);
	}
}
