package com.example.demo.service;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.util.CategoryExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found category by id"));
	}

	@Override
	public List<Category> getCategoryByName(String name) {
		return categoryRepository.findByCategoryNameContaining(name);
	}

	@Override
	public Category getACategoryByName(String name) {
		return categoryRepository.findByCategoryName(name);
	}

	@Override
	public int saveCategory(Category category) {
		categoryRepository.save(category);
		String folder = "./uploads/" + category.getSlug();
		try {

			Path path = Paths.get(folder);

			//java.nio.file.Files;
			Files.createDirectories(path);

			System.out.println("Directory is created!");

		} catch (IOException e) {

			System.err.println("Failed to create directory!" + e.getMessage());

		}
		return 1;
	}

	@Override
	public int deleteCategory(int id) {
		categoryRepository.findById(id).map(category -> {
			categoryRepository.deleteById(id);
			return 1;
		}).orElseThrow(() -> new NotFoundException("Not found category by id"));
		return 1;
	}

	@Override
	public void importExcel(MultipartFile file) {
		try {
			List<Category> categories = CategoryExcelUtils.excelToCategories(file.getInputStream());
			categoryRepository.saveAll(categories);
		} catch (IOException e) {
			throw new RuntimeException("Fail to store excel data: " + e.getMessage());
		}
	}

	@Override
	public ByteArrayInputStream exportExcel() {
		List<Category> categories = categoryRepository.findAll();
		ByteArrayInputStream in = CategoryExcelUtils.categoriesToExcel(categories);
		return in;
	}

}
