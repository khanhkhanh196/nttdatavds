package com.example.demo.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

@Component
public class ProductDTOConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public ProductDTO convertToProductDTO(Product product) {
		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
		if (product != null) {
			dto.setCategoriesSet(product.getCategoriesSet());
			dto.setFiles(product.getFiles());
		}
		return dto;
	}

	public Product convertProductDtoToEntity(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		return product;
	}

}
