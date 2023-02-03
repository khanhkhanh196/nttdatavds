package com.example.demo.restapi;


import com.example.demo.config.KeyCloakConfig;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackageClasses = { KeyCloakConfig.class , KeycloakSpringBootConfigResolver.class })
public class FileControllerTest extends BaseControllerTest {
    @Autowired
    MockMvc mockMvc;
    private MockMultipartFile file;

    private static final String categoryName = "Category name";
    private static final String categorySlug = "category-name";
    @Before
    public void setup() {
        file = new MockMultipartFile("file", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());
    }

    @Test
    public void uploadFileTest_201() throws Exception {
        configureSecurityContext("admin");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/rest/upload-file/file-category/{categoryName}", categoryName)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void uploadFileTest_403() throws Exception {
        configureSecurityContext("khanh","user");

        mockMvc.perform(multipart("/rest/upload-file/file-category/{categoryName}", categoryName)
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isForbidden()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void uploadMultipleFileTest() throws Exception {
        configureSecurityContext("admin");
        MockMultipartFile file2 = new MockMultipartFile("files", "fooo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());
        MockMultipartFile file3 = new MockMultipartFile("files", "foooo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());

        mockMvc.perform(multipart("/rest/upload-files/file-category/{categoryName}", categoryName)
                .file(file2)
                .file(file3)
                .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void downloadFileTestNotFound() throws Exception {
        configureSecurityContext("khanh","user");
        String filename = "";

        mockMvc.perform(get("/rest/file/{categorySlug}/{fileName}",categorySlug, filename)

        ).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void downloadFileTest() throws Exception {
        configureSecurityContext("khanh","user");
        String filename = "foo.txt";

        mockMvc.perform(get("/rest/file/{categorySlug}/{fileName}",categorySlug, filename)

        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
