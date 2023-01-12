package com.example.demo.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "file")
@Data
@ToString
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
	private Set<Product> product;
}
