package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.BrandDAO;
import com.example.demo.entity.Brand;
import com.example.demo.service.serviceinterface.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandDAO brandDao;
	
	@Override
	public Brand getBrand(int theId) {
		return brandDao.getById(theId);
	}

	@Override
	public void saveBrand(Brand brand) {
		brandDao.saveBrand(brand);
	}

}
