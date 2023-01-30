package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.ProductRepository;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.service.serviceinterface.ProductService;

import javax.transaction.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Transactional
	@Override
	public Product getProduct(int theId) {
		return productRepository.findById(theId).orElseThrow(() -> new NotFoundException("Not found product by id"));
	}

	@Transactional
	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Transactional
	@Override
	public void deleteProduct(int theId) {
		productRepository.deleteById(theId);
	}

	@Override
	public List<Product> getProductByCategoryName(String categoryName) {
		return productRepository.getAllProductByCategoryName(categoryName);
	}

	@Override
	public List<Product> getProductsByProductName(String productName) {
		return productRepository.findByProductNameContaining(productName);
	}

}
