package com.example.demo.dao;

import com.example.demo.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.daointerface.CategoryDAO;
import com.example.demo.dao.daointerface.CategoryJPA;
import com.example.demo.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private CategoryJPA categoryJPA;

	@Override
	public Category getById(int id) {
		return categoryJPA.findById(id).orElseThrow(() -> new CategoryNotFoundException("Not found category by id"));
	}

	@Override
	public Category getByName(String name) {
		return categoryJPA.findByCategoryName(name).orElseThrow(() -> new CategoryNotFoundException("Not found category by name"));
	}

	@Override
	public int saveCategory(Category category) {
		categoryJPA.save(category);
		return 1;
	}

	@Override
	public int deleteCategory(int id) {
		categoryJPA.findById(id).map(category -> {
			categoryJPA.deleteById(id);
			return 1;
		}).orElseThrow(() -> new CategoryNotFoundException("Not found category by id"));
		return 1;
	}
}
