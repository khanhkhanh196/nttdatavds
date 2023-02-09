package com.example.demo.service;


import com.example.demo.entity.Category;
import com.example.demo.entity.File;
import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.properties.FileStorageProperties;
import com.example.demo.repository.FileRepository;
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

    private FileRepository fileRepository = mock(FileRepository.class);;
    private FileStorageProperties storageProperties = mock(FileStorageProperties.class);;
    private MockMultipartFile file;
    String categorySLug = "image";
    Category category = new Category(0, "image",categorySLug);

    @BeforeEach
    public void setup() throws IOException {
        File entity = new File(0, "fileName", "fileDownloadUri", null);
        file = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());

        when(storageProperties.getUploadDir()).thenReturn("uploads");
        when(fileRepository.save(entity)).thenReturn(entity);

//        fileService = new FileServiceImpl(storageProperties, fileRepository);
    }

    @Test
    void storeFileInvalidNameThrows() {
        String fileName = "ContainsSpecial*#.jpg";
        String fileDownloadUri = "download/jpg";


        assertThrows(FileStorageException.class,
                () -> fileService.storeFile(file, fileName, fileDownloadUri,category));
    }

    @Test
    void storeFileValidName() {
        String fileName = "NotContainsSpecial.jpg";
        String fileDownloadUri = "download/jpg";

        assertEquals(fileService.storeFile(file, fileName, fileDownloadUri,category), fileName);
    }

    @Test
    void loadFileThrows() {
        String fileName = "foo.jps";

        assertThrows(NotFoundException.class,() -> {
            fileService.loadFileAsResource(fileName, categorySLug);
        });
    }

    @Test
    void loadFileSuccess() {
        when(storageProperties.getUploadDir()).thenReturn("src/main/resources/uploads");

        String fileName = "NotContainsSpecial.jpg";

        assertEquals(fileService.loadFileAsResource(fileName, categorySLug).getFilename(), fileName);
    }
}
