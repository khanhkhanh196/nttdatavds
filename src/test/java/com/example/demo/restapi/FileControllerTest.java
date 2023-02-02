package com.example.demo.restapi;


import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.WithMockKeycloakAuth;
import com.example.demo.config.BaseConfig;
import com.example.demo.config.KeyCloakConfig;
import com.example.demo.restapi.FileController;
import com.example.demo.service.FileServiceImpl;
import com.example.demo.service.serviceinterface.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackageClasses = { KeyCloakConfig.class , KeycloakSpringBootConfigResolver.class })
public class FileControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    private MockMultipartFile file;

    private String url = "rest/upload-image";
    @BeforeEach
    public void setup() throws IOException {
        file = new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello World".getBytes());
    }

    @Test
    public void uploadFileTest_201() throws Exception {
        configureSecurityContext("user","admin");

        mockMvc.perform(multipart("/rest/upload-image")
                        .file("file",file.getBytes())
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
