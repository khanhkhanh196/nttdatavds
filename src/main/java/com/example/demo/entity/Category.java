package com.example.demo.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

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

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "category_product",
			joinColumns = @JoinColumn(name = "category_reference_product_id"),
			inverseJoinColumns = @JoinColumn(name = "product_references_category_id"))
	Set<Product> productsSet;
}
