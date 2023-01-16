package com.example.demo.restapi;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.File;
import com.example.demo.payload.response.UploadFileResponse;
import com.example.demo.service.FileServiceImpl;
import com.example.demo.service.serviceinterface.FileService;

@RestController
@RequestMapping(Constants.REST_MAPPING)
public class FileController {
	@Autowired
	private FileService fileService;

	private static final String DOWNLOAD_FILE = "/downloadFile/";
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@PostMapping("/files")
	public void addNewFile(@Validated @RequestBody File file) {
		file.setFileId(0);
		fileService.saveFile(file);
	}

	@Autowired
	private FileServiceImpl fileStorageService;

	@PostMapping("/uploadFiles")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(Constants.REST_MAPPING + DOWNLOAD_FILE)
				.path(fileName).toUriString();

		fileStorageService.storeFile(file, fileName, fileDownloadUri);

		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> this.uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping(DOWNLOAD_FILE + "{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

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

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
