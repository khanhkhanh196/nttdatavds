package com.example.demo.seconddb.entity;

import com.example.demo.entity.Product;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
@Data
@ToString
public class SecondCategory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
	private int categoryId;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="slug")
	private String slug;

	public SecondCategory() {

	}

	public SecondCategory(int id, String name, String slug) {
		this.categoryId = id;
		this.categoryName = name;
		this.slug = slug;
	}

}
