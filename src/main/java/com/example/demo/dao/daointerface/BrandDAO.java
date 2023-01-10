package com.example.demo.dao.daointerface;

import com.example.demo.entity.Brand;

public interface BrandDAO {
	public Brand getById(int id);
	
	public void saveBrand(Brand brand);

}
