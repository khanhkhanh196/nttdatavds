package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="brand")
@Data
@ToString
public class Brand {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="brand_id")
	private int id;
	@Column(name="brand_name")
	private String brandName;
	@Column(name="slug")
	private String slug;
}
