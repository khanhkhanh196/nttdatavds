package com.example.demo;

import com.example.demo.dao.FileDAOImpl;
import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.MyFileNotFoundException;
import com.example.demo.properties.FileStorageProperties;
import com.example.demo.service.FileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class FileServiceTest {
    @InjectMocks
    FileServiceImpl fileService = mock(FileServiceImpl.class);

    FileStorageProperties filePro = mock(FileStorageProperties.class);
    FileDAOImpl fileDAO = mock(FileDAOImpl.class);
    MockMultipartFile file = mock(MockMultipartFile.class);

    private static final String DOWNLOAD_FILE = "/downloadFile/";

    @BeforeEach
    public void setup() {
    }
    @Test
    void storeFileThrows() {
        String fileName = "ContainsSpecial*#.jpg";

        String fileDownloadUri = "download/jpg";
        assertThrows(FileStorageException.class,() -> {
            fileService.storeFile(file, fileName, fileDownloadUri);
        });
    }

    @Test
    void loadFileThrows() {
        String fileName = "ContainsSpecial*";
        assertThrows(MyFileNotFoundException.class,() -> {
            fileService.loadFileAsResource(fileName);
        });
    }
}
