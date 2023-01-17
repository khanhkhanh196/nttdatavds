package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

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

	@ManyToMany(mappedBy = "productsSet")
	Set<Category> categoriesSet;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "product_file",
			joinColumns = @JoinColumn(name = "product_references_file_id"),
			inverseJoinColumns = @JoinColumn(name = "file_references_product_id")
	)
	private List<File> files;

}
