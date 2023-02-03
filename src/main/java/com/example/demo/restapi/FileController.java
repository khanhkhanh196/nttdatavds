package com.example.demo.restapi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.Constants;
import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.payload.response.UploadFileResponse;
import com.example.demo.service.serviceinterface.FileService;

@RestController
@RequestMapping(Constants.REST_MAPPING)
public class FileController {

	private CategoryService categoryService;
	private FileService fileService;

	@Autowired
	public FileController(FileService fileService, CategoryService categoryService) {
		this.fileService = fileService;
		this.categoryService = categoryService;
	}

	private static final String FILE_URL = "/file/";
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@PostMapping("/upload-file/file-category/{categoryName}")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("categoryName") String categoryName) {
		Category category = categoryService.getACategoryByName(categoryName);

		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(Constants.REST_MAPPING + FILE_URL).path(category.getSlug() + "/")
				.path(fileName).toUriString();

		fileService.storeFile(file, fileName, fileDownloadUri, category);

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(),categoryName,file.getSize());
	}

	@PostMapping("/upload-files/file-category/{categoryName}")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files, @PathVariable("categoryName") String categoryName) {
		return files.stream().map(file -> uploadFile(file,categoryName)).collect(Collectors.toList());
	}

	@GetMapping(FILE_URL +"{categorySlug}/"+"{fileName}")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile(@PathVariable("categorySlug") String categorySlug,
											   @PathVariable("fileName") String fileName, HttpServletRequest request) throws IOException {
		// Load file as Resource
		Resource resource = fileService.loadFileAsResource(fileName, categorySlug);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(contentType));

		return new ResponseEntity<>(resource.getInputStream().readAllBytes(), headers, HttpStatus.OK);
	}
}
