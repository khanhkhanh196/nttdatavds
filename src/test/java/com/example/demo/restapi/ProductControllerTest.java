package com.example.demo.restapi;

import com.example.demo.config.KeyCloakConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ComponentScan(basePackageClasses = { KeyCloakConfig.class , KeycloakSpringBootConfigResolver.class })
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProductByParam() {
    }

    @Test
    void getSingleProductByProductId() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void addNewProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getImageURLByProductId() {
    }
}