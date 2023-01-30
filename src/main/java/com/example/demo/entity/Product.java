package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "product")
@Data
@ToString
public class Product {
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

	@ManyToMany(cascade = {CascadeType.DETACH,  CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "category_product",
			joinColumns = @JoinColumn(name = "product_references_category_id"),
			inverseJoinColumns = @JoinColumn(name = "category_reference_product_id"))
	List<Category> categoriesSet;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "product_file",
			joinColumns = @JoinColumn(name = "product_references_file_id"),
			inverseJoinColumns = @JoinColumn(name = "file_references_product_id")
	)
	private List<File> files;

	@JsonIgnore
	public void setCategoriesSet(List<Category> categoriesSet) {
		this.categoriesSet = categoriesSet;
	}

	public ProductDTO convertToProductDTO() {
		ModelMapper modelMapper = new ModelMapper();
		ProductDTO dto = modelMapper.map(this, ProductDTO.class);
		if (this != null) {
			dto.setCategoriesSet(this.getCategoriesSet());
			dto.setFiles(this.getFiles());
		}
		return dto;
	}
}
