package com.example.demo.service;


import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.converter.DTOConverter;
import com.example.demo.entity.Category;
import com.example.demo.entity.File;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.restapi.ProductController;
import com.example.demo.service.serviceinterface.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    public static final int PRODUCT_ID = 1;
    public static final String NAME = "NAME";
    public static final String SHORT_DESC = "DESCRIPTION";
    public static final int STOCK = 1;
    public static final int SOLD = 1;
    public static final int PRICE = 1;
    public static final List<Category> CATEGORIES_SET = new ArrayList<>();
    public static final List<File> FILES = new ArrayList<>();

    @Mock
    ProductRepository productRepository;

    @Mock
    DTOConverter dtoConverter;

    @InjectMocks
    ProductService productServiceTest = new ProductServiceImpl();

    Product PRODUCT_RECORD;
    List<Product> PRODUCT_LIST = new ArrayList<>();
    ProductDTO PRODUCT_DTO;

    @BeforeEach
    void setUp() {
        PRODUCT_RECORD = new Product(PRODUCT_ID, NAME, SHORT_DESC, STOCK, SOLD, PRICE, new ArrayList<>(), new ArrayList<>());
        PRODUCT_LIST.add(PRODUCT_RECORD);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllProduct() {
        Mockito.when(productRepository.findAll()).thenReturn(PRODUCT_LIST);
        List<Product> result = productServiceTest.getAllProduct();
        Assertions.assertEquals(result.size(), PRODUCT_LIST.size());
    }

    @Test
    void getProductByProductId() {
        Mockito.when(productRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(PRODUCT_RECORD));
        Product result = productServiceTest.getProduct(PRODUCT_RECORD.getProductId());
        Assertions.assertEquals(result.getProductId(), PRODUCT_ID);
        Assertions.assertEquals(result.getProductName(), NAME);
        Assertions.assertEquals(result.getShortDesc(), SHORT_DESC);
        Assertions.assertEquals(result.getStock(), STOCK);
        Assertions.assertEquals(result.getSold(), SOLD);
        Assertions.assertEquals(result.getPrice(), PRICE);
    }

    @Test
    void getProductByCategoryName() {
        Mockito.when(productRepository.getAllProductByCategoryName(Mockito.any(String.class))).thenReturn(PRODUCT_LIST);
        List<Product> result = productServiceTest.getProductByCategoryName("");
        Assertions.assertEquals(result.size(), PRODUCT_LIST.size());
    }
}
