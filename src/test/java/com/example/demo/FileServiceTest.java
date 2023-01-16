package com.example.demo;

import com.example.demo.dao.FileDAOImpl;
import com.example.demo.dao.daointerface.FileDAO;
import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.MyFileNotFoundException;
import com.example.demo.properties.FileStorageProperties;
import com.example.demo.service.FileServiceImpl;
import com.example.demo.service.serviceinterface.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    private static final String DOWNLOAD_FILE = "/downloadFile/";

    private FileService fileService;
    private FileStorageProperties storageProperties;
    private FileDAO fileDAO;
    private MockMultipartFile file;

    @BeforeEach
    public void setup() {
        fileDAO = mock(FileDAOImpl.class);
        storageProperties = mock(FileStorageProperties.class);
        when(storageProperties.getUploadDir()).thenReturn("test");
        file = mock(MockMultipartFile.class);
        fileService = new FileServiceImpl(storageProperties, fileDAO);
    }

    @Test
    void storeFileThrows() {
        String fileName = "ContainsSpecial*#.jpg";
        String fileDownloadUri = "download/jpg";
        assertThrows(FileStorageException.class,
                () -> fileService.storeFile(file, fileName, fileDownloadUri));
    }

    @Test
    void loadFileThrows() {
        String fileName = "ContainsSpecial*";
        assertThrows(MyFileNotFoundException.class,() -> {
            fileService.loadFileAsResource(fileName);
        });
    }
}
