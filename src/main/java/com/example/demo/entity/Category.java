package com.example.demo.entity;

import javax.persistence.*;

import com.example.demo.dto.CategoryDTO;
import lombok.Data;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
@Data
@ToString
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
	private int categoryId;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="slug")
	private String slug;

	public Category() {

	}

	public Category(int id, String name, String slug) {
		this.categoryId = id;
		this.categoryName = name;
		this.slug = slug;
	}

	@ManyToMany(mappedBy = "categoriesSet")
	List<Product> productsSet;

	public CategoryDTO convertToCategoryDTO() {
		ModelMapper modelMapper = new ModelMapper();
		CategoryDTO dto = modelMapper.map(this, CategoryDTO.class);
		return dto;
	}

}
