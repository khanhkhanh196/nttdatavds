package com.example.demo.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.converter.DTOConverter;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.serviceinterface.CategoryService;
import com.example.demo.service.serviceinterface.ProductService;

@RestController
@RequestMapping("/rest")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService service;

	@Autowired
	private DTOConverter converter;

	@GetMapping("/products")
	public List<ProductDTO> getAllProduct() {
		List<Product> allProduct = productService.getAllProduct();
		List<ProductDTO> productDTOList = new ArrayList<>();
		for (Product product : allProduct) {
			ProductDTO productDTO = converter.convertToProductDTO(product);
			productDTOList.add(productDTO);
		}
		return productDTOList;
	}

	@GetMapping("/product/categoryName")
	public List<Product> getProductByCategoryName(@RequestParam String categoryName) {
		return productService.getProductByCategoryName(categoryName);
	}

	@GetMapping("/product/productName")
	public List<Product> getProductsByProductName(@RequestParam String productName) {
		return productService.getProductsByProductName(productName);
	}

	@GetMapping("/products/{id}")
	public ProductDTO getSingleProduct(@PathVariable int id) {
		Product product = productService.getProduct(id);
		if (product == null) {
			throw new ProductNotFoundException("Product not found " + id);
		}
		ProductDTO productDTO = converter.convertToProductDTO(product);
		return productDTO;
	}

	@PutMapping("/products")
	public void updateProduct(@Validated @RequestBody ProductDTO productDTO) {
		Product existedProduct = productService.getProduct(productDTO.getId());
		if (existedProduct != null) {
			Product product = null;
			product = converter.convertProductDtoToEntity(productDTO);
			productService.saveProduct(product);
		} else {
			throw new ProductNotFoundException("Product not found " + productDTO.getId());
		}
	}

	@PostMapping("/products")
	public void addNewProduct(@RequestBody ProductDTO productDTO) {
		Product product = converter.convertProductDtoToEntity(productDTO);
		productService.saveProduct(product);

	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		Product existedProduct = productService.getProduct(id);
		if (existedProduct != null) {
			productService.deleteProduct(id);
		} else {
			throw new ProductNotFoundException("Product not found " + id);
		}
	}
}
