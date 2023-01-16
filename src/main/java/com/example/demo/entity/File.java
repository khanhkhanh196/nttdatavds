package com.example.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

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
	@Column(name = "content_type")
	private String contentType;

	@ManyToMany(mappedBy = "files")
	private Set<Product> product;
}
