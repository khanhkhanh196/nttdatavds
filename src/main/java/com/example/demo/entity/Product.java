package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.demo.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "stock")
	private int stock;

	@Column(name = "sold")
	private int sold;

	@Column(name = "price")
	private double price;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "category_product",
			joinColumns = @JoinColumn(name = "product_references_category_id"),
			inverseJoinColumns = @JoinColumn(name = "category_references_product_id"))
	List<Category> categoriesSet;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "product_file",
			joinColumns = @JoinColumn(name = "product_references_file_id"),
			inverseJoinColumns = @JoinColumn(name = "file_references_product_id")
	)
	private List<File> files;

	public ProductDTO convertToProductDTO() {
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO dto = modelMapper.map(this, ProductDTO.class);
		List<Integer> categoryIds = new ArrayList<>();
		List<Integer> fileIds = new ArrayList<>();
		for (Category category :
			 categoriesSet) {
			categoryIds.add(category.getCategoryId());
		}
		for (File file :
				files) {
			fileIds.add(file.getFileId());
		}
		dto.setCategoriesIds(categoryIds);
		dto.setFilesIds(fileIds);
		return dto;
	}


}
