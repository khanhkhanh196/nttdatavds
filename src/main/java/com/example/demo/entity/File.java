package com.example.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "file")
@Data
@ToString
@AllArgsConstructor
public class File {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id")
	private int fileId;
	@Column(name = "file_name")
	private String file_name;
	@Column(name = "url")
	private String url;

	@ManyToMany(mappedBy = "files")
	private List<Product> product;
	@JsonIgnore
	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
