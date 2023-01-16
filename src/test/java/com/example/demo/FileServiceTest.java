package com.example.demo;

import com.example.demo.common.Constants;
import com.example.demo.exception.FileStorageException;
import com.example.demo.service.FileServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class FileServiceTest {
//    @Autowired
//    FileServiceImpl fileService;
//    MultipartFile file = mock(MultipartFile.class);
//    private static final String DOWNLOAD_FILE = "/downloadFile/";
//
//    @Test
//    void storeFileThrows() {
//
//        String fileName = "ContainsSpecial*";
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(Constants.REST_MAPPING + DOWNLOAD_FILE)
//                .path(fileName).toUriString();
//        assertThrows(FileStorageException.class,() -> {
//            fileService.storeFile(file,fileName,fileDownloadUri);
//        });
//
//    }
}
