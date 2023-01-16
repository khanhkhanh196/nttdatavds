package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.CategoryDAO;
import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public Category getCategoryById(int theId) {
		return categoryDao.getById(theId);
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryDao.getByName(name);
	}

	@Override
	public int saveCategory(Category category) {
		categoryDao.saveCategory(category);
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
	public int deleteCategory(int categoryId) {
		categoryDao.deleteCategory(categoryId);
		return 1;
	}

}
