package com.example.demo.service.serviceinterface;

import com.example.demo.entity.Category;

public interface CategoryService {
	public Category getCategoryById(int theId);
	public Category getCategoryByName(String name);
	public void saveCategory(Category category);
	public void deleteCategory(int categoryId);
}
