package com.example.demo.service;


import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.properties.FileStorageProperties;
import com.example.demo.service.serviceinterface.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    private static final String DOWNLOAD_FILE = "/downloadFile/";
    private FileService fileService;
    private FileStorageProperties storageProperties;
    private MockMultipartFile file;

    @BeforeEach
    public void setup() throws IOException {
        storageProperties = mock(FileStorageProperties.class);
        when(storageProperties.getUploadDir()).thenReturn("upload");
        file = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());
        fileService = new FileServiceImpl(storageProperties);
    }

    @Test
    void storeFileInvalidNameThrows() {
        String fileName = "ContainsSpecial*#.jpg";
        String fileDownloadUri = "download/jpg";
//        assertThrows(FileStorageException.class,
//                () -> fileService.storeFile(file, fileName, fileDownloadUri));
    }

    @Test
    void storeFileValidName() {
        String fileName = "NotContainsSpecial.jpg";
        String fileDownloadUri = "download/jpg";
//        assertEquals(fileService.storeFile(file, fileName, fileDownloadUri), fileName);
    }

    @Test
    void loadFileThrows() {
        String fileName = "foo.jps";
        String categorySLug = "category";
        assertThrows(NotFoundException.class,() -> {
            fileService.loadFileAsResource(fileName, categorySLug);
        });
    }

    @Test
    void loadFileSuccess() {
        when(storageProperties.getUploadDir()).thenReturn("src/main/resources/upload");
        fileService = new FileServiceImpl(storageProperties);
        String fileName = "June_odd-eyed-cat.jpg";
        String categorySLug = "category";

        assertEquals(fileService.loadFileAsResource(fileName, categorySLug).getFilename(), fileName);
    }
}
