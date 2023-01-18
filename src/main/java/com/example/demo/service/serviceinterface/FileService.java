package com.example.demo.service.serviceinterface;

import com.example.demo.entity.File;

import java.util.List;

public interface FileService {
	public void saveFile(File file);
	public List<String> getImageURLByProductId(int productId);
}
