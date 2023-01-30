package com.example.demo.entity;

import javax.persistence.*;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "file")
@Getter
@Setter
@NoArgsConstructor
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

}
