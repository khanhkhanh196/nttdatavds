package com.example.demo.dao.daointerface;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductDAO {
	public List<Product> getAllProduct();

	public Product getProduct(int theId);

	public void saveProduct(Product product);

	public void deleteProduct(int theId);


}
