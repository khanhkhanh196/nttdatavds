package com.example.demo.service.serviceinterface;

import com.example.demo.entity.Brand;

public interface BrandService {
	public Brand getBrand(int theId);
	public void saveBrand(Brand brand);
}
