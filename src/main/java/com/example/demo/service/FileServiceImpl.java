package com.example.demo.service;

import com.example.demo.dao.daointerface.FileJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.FileDAO;
import com.example.demo.entity.File;
import com.example.demo.service.serviceinterface.FileService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDao;

	@Autowired
	private FileJPA fileJPA;
	
	@Override
	public void saveFile(File file) {
		fileDao.saveFile(file);
	}

	@Override
	public List<String> getImageURLByProductId(int productId) {
		List<File> files = fileJPA.getFilesByProductId(productId);
		List<String> imagesURL = new ArrayList<>();
		for (File file: files) {
			imagesURL.add(file.getUrl());
		}
		return imagesURL;
	}

}
