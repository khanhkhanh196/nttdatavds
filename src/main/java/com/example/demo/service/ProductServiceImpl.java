package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductDTO;
import com.example.demo.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.ProductDAO;
import com.example.demo.entity.Product;
import com.example.demo.service.serviceinterface.ProductService;

import javax.transaction.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDao;
	
	@Transactional
	@Override
	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}

	@Transactional
	@Override
	public Product getProduct(int theId) {
		return productDao.getProduct(theId);
	}

	@Transactional
	@Override
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	@Transactional
	@Override
	public void deleteProduct(int theId) {
		productDao.deleteProduct(theId);
	}

	@Override
	public List<Product> getProductByCategoryName(String name) {
		return productDao.getAllProductByCategoryName(name);
	}

}
