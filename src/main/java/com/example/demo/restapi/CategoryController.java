package com.example.demo.restapi;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.payload.response.PayloadResponse;
import com.example.demo.util.CategoryExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("")
	public ResponseEntity<PayloadResponse<CategoryDTO>> addNewCategory(@Validated @RequestBody CategoryDTO categoryDTO) {
		Category category = categoryDTO.convertToEntity();
		categoryService.saveCategory(category);
		PayloadResponse<CategoryDTO> payloadResponse = new PayloadResponse<>(201, "Category created", System.currentTimeMillis(), categoryDTO);
		return new ResponseEntity<>(payloadResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PayloadResponse<CategoryDTO>> findCategoryById(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		CategoryDTO dto = category.convertToCategoryDTO();
		PayloadResponse<CategoryDTO> payloadResponse = new PayloadResponse<>(200, "Category found", System.currentTimeMillis(), dto);
		return new ResponseEntity<>(payloadResponse, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<PayloadResponse<List<CategoryDTO>>> findCategoryByName(@RequestParam("name") String name) {
		List<Category> categories = categoryService.getCategoryByName(name);
		List<CategoryDTO> categoryDTOS = new ArrayList<>();
		for (Category category: categories) {
			categoryDTOS.add(category.convertToCategoryDTO());
		}
		PayloadResponse<List<CategoryDTO>> payloadResponse = new PayloadResponse<>(200, "Category found", System.currentTimeMillis(), categoryDTOS);
		return new ResponseEntity<>(payloadResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PayloadResponse<CategoryDTO>> deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
		PayloadResponse<CategoryDTO> payloadResponse = new PayloadResponse<>(200, "Category deleted", System.currentTimeMillis());
		return new ResponseEntity<>(payloadResponse, HttpStatus.OK);
	}

	@PostMapping("/import")
	public ResponseEntity<PayloadResponse<CategoryDTO>> importExcel(@RequestParam("file") MultipartFile file) {
		PayloadResponse<CategoryDTO> payloadResponse;
		if (CategoryExcelUtils.hasExcelFormat(file)) {
			try {
				categoryService.importExcel(file);
				payloadResponse = new PayloadResponse<>(200, "File " + file.getOriginalFilename() + " imported", System.currentTimeMillis());
				return ResponseEntity.status(HttpStatus.OK).body(payloadResponse);
			} catch (Exception e) {
				payloadResponse = new PayloadResponse<>(417, "Could not upload file " + file.getOriginalFilename(), System.currentTimeMillis());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(payloadResponse);
			}
		}
		payloadResponse = new PayloadResponse<>(400, "Please upload an excel file", System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.OK).body(payloadResponse);
	}

	@GetMapping("/export")
	public ResponseEntity<Resource> getFile() {
		String filename = "tutorials.xlsx";
		InputStreamResource file = new InputStreamResource(categoryService.exportExcel());

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(file);
	}
}
