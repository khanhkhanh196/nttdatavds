package com.example.demo.restapi;


import com.example.demo.config.BaseConfig;
import com.example.demo.restapi.FileController;
import com.example.demo.service.FileServiceImpl;
import com.example.demo.service.serviceinterface.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.mock;

@WebMvcTest(FileController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = BaseConfig.class,loader= AnnotationConfigContextLoader.class)
public class FileControllerTest {
    private FileController controller;
    private FileService fileService  = mock(FileServiceImpl.class);;
    private MockMultipartFile file;

    private ApplicationContext context;
    @BeforeEach
    private void setup(){
        controller = new FileController(fileService);
    }

    @Test
    public void uploadFileTest(){
        controller.uploadFile(file);
    }
}
