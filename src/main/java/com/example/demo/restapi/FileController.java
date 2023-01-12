package com.example.demo.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.File;
import com.example.demo.service.serviceinterface.FileService;

@RestController
@RequestMapping("/rest")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping("/files")
	public void addNewReview(@Validated @RequestBody File file) {
		file.setFileId(0);
		fileService.saveFile(file);
	}
}
