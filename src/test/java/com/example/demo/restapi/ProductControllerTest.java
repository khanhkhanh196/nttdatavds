package com.example.demo.restapi;

import com.example.demo.config.KeyCloakConfig;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.File;
import com.example.demo.entity.Product;
import com.example.demo.service.serviceinterface.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ComponentScan(basePackageClasses = { KeyCloakConfig.class , KeycloakSpringBootConfigResolver.class })
class ProductControllerTest extends BaseControllerTest {

    private String url = "/rest/products";

    public static final int PRODUCT_ID = 1;
    public static final String NAME = "NAME";
    public static final String SHORT_DESC = "DESCRIPTION";
    public static final int STOCK = 1;
    public static final int SOLD = 1;
    public static final int PRICE = 1;
    public static final List<Category> CATEGORIES_SET = new ArrayList<>();
    public static final List<File> FILES = new ArrayList<>();

    private Product product ;
    private ProductDTO productDTO;
    private List<ProductDTO> productDTOList = new ArrayList<>();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @BeforeEach
    void setUp() {
        product = new Product(PRODUCT_ID, NAME, SHORT_DESC, STOCK, SOLD, PRICE, new ArrayList<>(), new ArrayList<>());
        productDTO = new ProductDTO(PRODUCT_ID, NAME, SHORT_DESC, STOCK, SOLD, PRICE, new ArrayList<>(), new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProductByParam_return200OK() throws Exception {
        configureSecurityContext("user", "admin");
        Mockito.when(productService.getProduct(PRODUCT_ID)).thenReturn(product);

        ResultActions response = mockMvc.perform(get(url, PRODUCT_ID));
    }

    @Test
    void givenProductId_whenGetProductById_thenReturnProductObject() throws Exception{
        configureSecurityContext("user", "admin");
        //when
        Mockito.when(productService.getProduct(PRODUCT_ID)).thenReturn(product);
        //then
        mockMvc.perform(get(url + "/{" + PRODUCT_ID + "}", String.valueOf(PRODUCT_ID))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
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