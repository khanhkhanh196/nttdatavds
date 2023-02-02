package com.example.demo.restapi;



import com.example.demo.config.KeyCloakConfig;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackageClasses = { KeyCloakConfig.class , KeycloakSpringBootConfigResolver.class })
public class FileControllerTest {
    @Autowired
    MockMvc mockMvc;
    private MockMultipartFile file;
    @BeforeEach
    public void setup() throws IOException {
        file = new MockMultipartFile("file", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());
    }

    @Test
    public void uploadFileTest_201() throws Exception {
        configureSecurityContext("user","admin");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/rest/upload-image")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void uploadFileTest_403() throws Exception {
        configureSecurityContext("khanh","user");

        mockMvc.perform(multipart("/rest/upload-image")
                        .file("file",file.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isForbidden()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void downloadFileTestNotFound() throws Exception {
        configureSecurityContext("khanh","user");
        String filename = "";

        mockMvc.perform(get("/rest/download-image/" + filename)

        ).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void downloadFileTest() throws Exception {
        configureSecurityContext("khanh","user");
        String filename = "June_odd-eyed-cat.jpg";

        mockMvc.perform(get("/rest/download-image/" + filename)

        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    private void configureSecurityContext(String... roles) {
        final var principal = mock(Principal.class);

        final var account = mock(OidcKeycloakAccount.class);
        when(account.getRoles()).thenReturn(new HashSet<>(Arrays.asList(roles)));
        when(account.getPrincipal()).thenReturn(principal);

        final var authentication = mock(KeycloakAuthenticationToken.class);
        when(authentication.getAccount()).thenReturn(account);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
