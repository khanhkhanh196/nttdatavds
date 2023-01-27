package com.example.demo.restapi;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryServiceImpl;
import com.example.demo.service.serviceinterface.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
    @Autowired
//    @Mock
    MockMvc mockMvc;

    @MockBean
//    @Mock
    CategoryServiceImpl categoryService;

    private int id = 1;

    private String url = "/rest/categories";

    private String name = "category name";

    List<Category> categories = new ArrayList<>();

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addNewCategory_return201() throws Exception {
        // Given
        Category category = new Category(id, "category name", "category-name");
        CategoryDTO categoryDTO = new CategoryDTO("category name", "category-name");

        // When
        Mockito.when(categoryDTO.convertToEntity()).thenReturn(category);
        Mockito.when(categoryService.saveCategory(category)).thenReturn(1);

        // Then
        mockMvc.perform(post("/rest/categories")
                        .content(asJsonString(categoryDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void findCategoryById_return200() throws Exception {
        Category category = new Category(id, "category name", "category-name");
        CategoryDTO categoryDTO = new CategoryDTO("category name", "category-name");

        // When
        Mockito.when(categoryService.getCategoryById(id)).thenReturn(category);
        Mockito.when(category.convertToCategoryDTO()).thenReturn(categoryDTO);


        // Then
        mockMvc.perform(get("/rest/categories/{id}", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findCategoryByName_return200() throws Exception {
        Category category = new Category(id, "category name", "category-name");
        CategoryDTO categoryDTO = new CategoryDTO("category name", "category-name");
        categories.add(category);

        // When
        Mockito.when(categoryService.getCategoryByName(name)).thenReturn(categories);
        Mockito.when(category.convertToCategoryDTO()).thenReturn(categoryDTO);


        // Then
        mockMvc.perform(get("/rest/categories")
                        .param("name", name))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCategory_return200() throws Exception {

        // When
        Mockito.when(categoryService.deleteCategory(id)).thenReturn(1);

        // Then
        mockMvc.perform(delete("/rest/categories/{id}", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
