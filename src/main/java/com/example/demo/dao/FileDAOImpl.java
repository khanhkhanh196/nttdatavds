package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.daointerface.FileDAO;
import com.example.demo.dao.daointerface.FileJPA;
import com.example.demo.entity.File;

@Repository
public class FileDAOImpl implements FileDAO {
	@Autowired
	private FileJPA fileJPA;

	@Override
	public void saveFile(File file) {
		fileJPA.save(file);
	}

}
