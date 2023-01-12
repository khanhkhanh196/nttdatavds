package com.example.demo.service.serviceinterface;

import com.example.demo.entity.Category;

public interface CategoryService {
	public Category getCategory(int theId);
	public void saveCategory(Category category);
}
