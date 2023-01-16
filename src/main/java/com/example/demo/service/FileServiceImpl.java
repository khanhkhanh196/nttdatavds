package com.example.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.common.Regex;
import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.daointerface.FileDAO;
import com.example.demo.entity.File;
import com.example.demo.properties.FileStorageProperties;
import com.example.demo.service.serviceinterface.FileService;
@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileDAO fileDao;
	
	private final Path fileStorageLocation;

	@Autowired
	public FileServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
	            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public String storeFile(MultipartFile file,String fileName, String fileDownloadUri) {
		try {
			Matcher matcher = Regex.noSpecialChar.matcher(fileName);
			boolean inValidFileName = matcher.find();
			// Check if the file's name contains invalid characters
			if (inValidFileName) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			File entity = new File(0, fileName, fileDownloadUri, file.getContentType(), null);
			this.saveFile(entity);
			return fileName;
		} catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
	                throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	
	@Override
	public void saveFile(File file) {
		fileDao.saveFile(file);
	}
}
