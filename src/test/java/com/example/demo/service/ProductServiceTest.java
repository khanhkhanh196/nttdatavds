package com.example.demo.service;


import com.example.demo.entity.Category;
import com.example.demo.entity.File;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class ProductServiceTest {

    public static final int PRODUCT_ID = 1;
    public static final String NAME = "NAME";
    public static final String SHORT_DESC = "DESCRIPTION";
    public static final int STOCK = 1;
    public static final int SOLD = 1;
    public static final int PRICE = 1;
    public static final List<Category> CATEGORIES_SET = new ArrayList<>();
    public static final List<File> FILES = new ArrayList<>();

    @Autowired
    ProductServiceImpl productServiceTest;

    @Autowired
    ProductRepository productTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllProduct() {
        // given

    }

    @Test
    void getProduct() {
    }

    @Test
    void saveProduct() {
        //given
//        Product product = new Product(PRODUCT_ID, NAME, SHORT_DESC, STOCK, SOLD, PRICE, CATEGORIES_SET, FILES);
//        productServiceTest.saveProduct(product);
//
//        //when
//        Optional<Product> expectedProduct = productTest.findById(PRODUCT_ID);

        //then
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductByCategoryName() {
    }
}