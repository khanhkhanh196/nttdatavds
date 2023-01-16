package com.example.demo;

import com.example.demo.dao.daointerface.FileDAO;
import com.example.demo.properties.FileStorageProperties;
import com.example.demo.restapi.FileController;
import com.example.demo.service.FileServiceImpl;
import com.example.demo.service.serviceinterface.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Mockito.mock;

@WebMvcTest(FileController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class FileControllerTest {
    private FileController controller;
    private FileService fileService  = mock(FileServiceImpl.class);;
    private MockMultipartFile file;
    @BeforeEach
    private void setup(){
//        fileDAO = mock(FileDAOImpl.class);
//        storageProperties = mock(FileStorageProperties.class);
//        when(storageProperties.getUploadDir()).thenReturn("test");
//        file = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
//                "Hello World".getBytes());
//        fileService = new FileServiceImpl(storageProperties, fileDAO);
        controller = new FileController(fileService);
    }

    @Test
    public void uploadFileTest(){
        controller.uploadFile(file);
    }
}
