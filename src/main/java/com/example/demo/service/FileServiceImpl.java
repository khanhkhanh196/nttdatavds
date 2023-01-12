package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.FileDAO;
import com.example.demo.entity.File;
import com.example.demo.service.serviceinterface.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDao;
	
	@Override
	public void saveFile(File file) {
		fileDao.saveFile(file);
	}

}
