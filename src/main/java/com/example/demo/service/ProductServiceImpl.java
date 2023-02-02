package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.File;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.serviceinterface.CategoryService;
import com.example.demo.service.serviceinterface.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.service.serviceinterface.ProductService;

import javax.transaction.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FileService fileService;
	
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
	public void saveProduct(ProductDTO productDTO) {

		ModelMapper modelMapper = new ModelMapper();
		Product product = modelMapper.map(productDTO, Product.class);

		List<Category> categoryList = new ArrayList<>();
		List<File> fileList = new ArrayList<>();

		for (Integer categoryID:
				productDTO.getCategoriesIds()) {
			Category category = categoryService.getCategoryById(categoryID);
			categoryList.add(category);
		}

		for (Integer fileId :
				productDTO.getFilesIds()) {
			File file = fileService.getFileById(fileId);
			fileList.add(file);
		}

		product.setCategoriesSet(categoryList);
		product.setFiles(fileList);
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
