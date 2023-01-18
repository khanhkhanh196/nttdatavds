package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.daointerface.ProductDAO;
import com.example.demo.dao.daointerface.ProductJPA;
import com.example.demo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private ProductJPA productJpa;

	@Override
	public List<Product> getAllProduct() {
		return productJpa.findAll();
	}

	@Override
	public Product getProduct(int theId) {
		return productJpa.findById(theId).get();
	}

	@Override
	public void saveProduct(Product product) {
		productJpa.save(product);
	}

	@Override
	public void deleteProduct(int theId) {
		// TODO Auto-generated method stub
		productJpa.deleteById(theId);
	}

	@Override
	public List<Product> getAllProductByCategoryName(String categoryName) {
		return productJpa.getAllProductByCategoryName(categoryName);
	}

	@Override
	public List<Product> getProductsByProductName(String productName) {
		return productJpa.findByProductNameContaining(productName);
	}


}
