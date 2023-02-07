package com.example.demo.service.serviceinterface;

import com.example.demo.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface CategoryService {
	public Category getCategoryById(int theId);
	public List<Category> getCategoryByName(String name);
	public Category getACategoryByName(String name);
	public int saveCategory(Category category);
	public int deleteCategory(int categoryId);
	public void importExcel(MultipartFile file);
	public ByteArrayInputStream exportExcel();
}
