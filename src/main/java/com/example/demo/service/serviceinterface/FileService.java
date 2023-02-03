package com.example.demo.service.serviceinterface;

import com.example.demo.entity.Category;
import com.example.demo.entity.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
	public void saveFile(File file);

	String storeFile(MultipartFile file, String fileName, String fileDownloadUri, Category categorySlug);

	Resource loadFileAsResource(String fileName, String categorySlug);
	public List<String> getImageURLByProductId(int productId);

	public File getFileById(int id);
}
