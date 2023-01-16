package com.example.demo.dao.daointerface;

import com.example.demo.entity.Category;

public interface CategoryDAO {
	public Category getById(int id);

	public Category getByName(String name);
	
	public int saveCategory(Category category);

	public int deleteCategory(int id);

}
