package com.example.demo.service;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;

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

}
