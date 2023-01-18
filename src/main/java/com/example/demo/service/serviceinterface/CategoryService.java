package com.example.demo.service.serviceinterface;

import com.example.demo.entity.Category;

public interface CategoryService {
	public Category getCategoryById(int theId);
	public Category getCategoryByName(String name);
	public int saveCategory(Category category);
	public int deleteCategory(int categoryId);
}
