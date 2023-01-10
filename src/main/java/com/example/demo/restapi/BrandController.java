package com.example.demo.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Brand;
import com.example.demo.service.serviceinterface.BrandService;

@RestController
@RequestMapping("/rest")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@PostMapping("/brands")
	public void addNewProduct(@Validated @RequestBody Brand brand) {
		brand.setId(0);
		brandService.saveBrand(brand);
	}
}
