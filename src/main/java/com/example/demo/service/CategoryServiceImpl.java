package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.CategoryDAO;
import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;

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
	public void saveCategory(Category category) {
		categoryDao.saveCategory(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryDao.deleteCategory(categoryId);
	}

}
