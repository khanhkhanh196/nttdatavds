package com.example.demo.dto.converter;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOConverter {

//	@Autowired
//	private ModelMapper modelMapper;
//
//	public ProductDTO convertToProductDTO(Product product) {
//		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
//		if (product != null) {
//			dto.setCategoriesSet(product.getCategoriesSet());
//			dto.setFiles(product.getFiles());
//		}
//		return dto;
//	}
//
//	public Product convertProductDtoToEntity(ProductDTO productDTO) {
//		Product product = modelMapper.map(productDTO, Product.class);
//		return product;
//	}
//
//	public Category convertCategoryDtoToEntity(CategoryDTO categoryDTO) {
//		Category category = modelMapper.map(categoryDTO, Category.class);
//		return category;
//	}
//
//	public CategoryDTO convertToCategoryDTO(Category category) {
//		CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
//		return dto;
//	}
    public List<ProductDTO> convertProductListToProductDTOList(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            ProductDTO productDTO = product.convertToProductDTO();
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

}
