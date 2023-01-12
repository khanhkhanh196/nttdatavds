package com.example.demo.dao.daointerface;

import com.example.demo.entity.Category;

public interface CategoryDAO {
	public Category getById(int id);
	
	public void saveCategory(Category category);

}
