package com.example.demo.service.serviceinterface;

import java.util.List;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface ProductService {
	public List<Product> getAllProduct();

	public Product getProduct(int theId);

	public void saveProduct(ProductDTO productDTO);

	public void deleteProduct(int theId);

	public List<Product> getProductByCategoryName(String categoryName);

	public List<Product> getProductsByProductName(String productName);
}
