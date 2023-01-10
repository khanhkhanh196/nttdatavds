package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.daointerface.BrandDAO;
import com.example.demo.dao.daointerface.BrandJPA;
import com.example.demo.entity.Brand;

@Repository
public class BrandDAOImpl implements BrandDAO {
	@Autowired
	private BrandJPA brandJPA;

	@Override
	public Brand getById(int id) {
		return brandJPA.findById(id).get();
	}

	@Override
	public void saveBrand(Brand brand) {
		brandJPA.save(brand);
	}
}
