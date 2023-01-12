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
		return categoryJPA.findById(id).get();
	}

	@Override
	public void saveCategory(Category category) {
		categoryJPA.save(category);
	}

	@Override
	public void deleteCategory(int id) {
		categoryJPA.findById(id).map(category -> {
			categoryJPA.deleteById(id);
			return 0;
		}).orElseThrow(() -> new CategoryNotFoundException("Not found category by id"));

	}
}
