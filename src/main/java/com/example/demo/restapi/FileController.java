package com.example.demo.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.File;
import com.example.demo.service.serviceinterface.FileService;

import java.util.List;

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

	@GetMapping("files/ImageURL/productId")
	public List<String> getImageURLByProductId(@RequestParam int productId) {
		return fileService.getImageURLByProductId(productId);
	}
}
