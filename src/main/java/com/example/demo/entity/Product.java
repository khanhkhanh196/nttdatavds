package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	private int id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "stock")
	private int stock;

	@Column(name = "sold_quantity")
	private int soldQuantity;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "brand_detail_id")
	private Brand brand;

	@OneToMany(targetEntity = Review.class, mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Review> reviews;

}
