package com.example.demo.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.serviceinterface.CategoryService;
import com.example.demo.service.serviceinterface.ProductService;

@RestController
@RequestMapping("/rest")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService service;

	@GetMapping("/products")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProduct() {
		List<Product> allProduct = productService.getAllProduct();
		List<ProductDTO> productDTOList = new ArrayList<>();
		for (Product product : allProduct) {
			ProductDTO productDTO = product.convertToProductDTO();
			productDTOList.add(productDTO);
		}
		return ResponseEntity.ok(new ApiResponse<>("success", productDTOList, null));
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
			throw new NotFoundException("Product not found " + id);
		}
		ProductDTO productDTO = product.convertToProductDTO();
		return productDTO;
	}

	@PutMapping("/products")
	public void updateProduct(@Validated @RequestBody ProductDTO productDTO) {
		Product existedProduct = productService.getProduct(productDTO.getId());
		if (existedProduct != null) {
			Product product = null;
			product = productDTO.convertToEntity();
			productService.saveProduct(product);
		} else {
			throw new NotFoundException("Product not found " + productDTO.getId());
		}
	}

	@PostMapping("/products")
	public void addNewProduct(@RequestBody ProductDTO productDTO) {
		Product product = productDTO.convertToEntity();
		productService.saveProduct(product);

	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		Product existedProduct = productService.getProduct(id);
		if (existedProduct != null) {
			productService.deleteProduct(id);
		} else {
			throw new NotFoundException("Product not found " + id);
		}
	}
}
