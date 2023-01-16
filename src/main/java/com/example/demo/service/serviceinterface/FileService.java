package com.example.demo.service.serviceinterface;

import com.example.demo.entity.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public void saveFile(File file);

	String storeFile(MultipartFile file, String fileName, String fileDownloadUri);

	Resource loadFileAsResource(String fileName);
}
