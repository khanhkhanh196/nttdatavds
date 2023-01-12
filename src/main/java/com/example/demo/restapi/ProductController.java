package com.example.demo.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.converter.DTOConverter;
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

//	@GetMapping("/products")
//	public List<ProductDTO> getAllProduct() {
//		List<Product> allProduct = productService.getAllProduct();
//		List<ProductDTO> productDTOList = new ArrayList<>();
//		for (Product product : allProduct) {
//			ProductDTO productDTO = converter.convertToProductDTO(product);
//			productDTOList.add(productDTO);
//		}
//		return productDTOList;
//	}
//
//	@GetMapping("/products/{id}")
//	public ProductDTO getSingleProduct(@PathVariable int id) {
//		Product product = productService.getProduct(id);
//		if (product == null) {
//			throw new ProductNotFoundException("Product not found " + id);
//		}
//		ProductDTO productDTO = converter.convertToProductDTO(product);
//		return productDTO;
//	}

	@PutMapping("/products/{id}")
	public void updateProduct(@PathVariable int id, @Validated @RequestBody ProductDTO productDTO) {
		Product existedProduct = productService.getProduct(id);
		if (existedProduct != null && existedProduct.getProductId() == productDTO.getId()) {
			Product product = null;
			product = converter.convertProductDtoToEntity(productDTO);
			productService.saveProduct(product);
		} else {
			throw new ProductNotFoundException("Product not found " + id);
		}
	}

//	@PostMapping("/products")
//	public ResponseEntity<String> addNewProduct(@RequestBody ProductDTO productDTO) {
//		Product product = null;
//		product = converter.convertProductDtoToEntity(productDTO);
//		product.setProductId(0);
//		int brandId = productDTO.getBrand();
//		Category category = service.getCategory(brandId);
//		if (category == null) {
//			product.setCategory(category);
//		}
//		productService.saveProduct(product);
//
//		return new ResponseEntity<>(HttpStatus.ACCEPTED);
//	}

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
