package com.example.demo.restapi;

import java.util.List;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.converter.DTOConverter;
import com.example.demo.service.serviceinterface.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProductDTO;
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
	private CategoryService categoryService;

	@Autowired
	private FileService fileService;

	@Autowired
	private DTOConverter dtoConverter;


	@GetMapping("/products")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProduct() {
		List<Product> allProduct = productService.getAllProduct();
		List<ProductDTO> productDTOList = dtoConverter.convertProductListToProductDTOList(allProduct);
		return ResponseEntity.ok(new ApiResponse<>("success", productDTOList, null));
	} //run ok

	@GetMapping("/product/categoryName")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductByCategoryName(@RequestParam String categoryName) {
		List<Product> productList = productService.getProductByCategoryName(categoryName);
		List<ProductDTO> productDTOList = dtoConverter.convertProductListToProductDTOList(productList);
		return ResponseEntity.ok(new ApiResponse<>("success", productDTOList, null));
	} // run ok

	@GetMapping("/product/productName")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductsByProductName(@RequestParam String productName) {
		List<Product> productList = productService.getProductsByProductName(productName);
		List<ProductDTO> productDTOList = dtoConverter.convertProductListToProductDTOList(productList);
		return ResponseEntity.ok(new ApiResponse<>("success", productDTOList, null));
	} // run ok

	@GetMapping("/products/{id}")
	public ProductDTO getSingleProduct(@PathVariable int id) {
		Product product = productService.getProduct(id);
		if (product == null) {
			throw new NotFoundException("Product not found " + id);
		}
		ProductDTO productDTO = product.convertToProductDTO();
		return productDTO;
	} //run ok

	@PutMapping("/products")
	public void updateProduct(@Validated @RequestBody ProductDTO productDTO) {
		Product existedProduct = productService.getProduct(productDTO.getProductId());
		if (existedProduct != null) {
			productService.saveProduct(productDTO);
		} else {
			throw new NotFoundException("Product not found " + productDTO.getProductId());
		}
	} //run ok

	@PostMapping("/products")
	public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO productDTO) {
		productService.saveProduct(productDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	} // run ok

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable int id) {
		Product existedProduct = productService.getProduct(id);
		if (existedProduct != null) {
			productService.deleteProduct(id);
		} else {
			throw new NotFoundException("Product not found " + id);
		}
	} // run ok

	@GetMapping("files/ImageURL/productId")
	public List<String> getImageURLByProductId(@RequestParam int productId) {
		return fileService.getImageURLByProductId(productId);
	}

}
