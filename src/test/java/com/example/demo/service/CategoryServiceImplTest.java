package com.example.demo.service;

import com.example.demo.dao.daointerface.CategoryDAO;
import com.example.demo.entity.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryDAO categoryDAO;

    int id = 1;

    Category category = new Category(id, "category name", "category-name");

    @Before
    public void setUp() {

    }

    @Test
    public void whenGetCategoryById_returnTrue() {
        Mockito.when(categoryDAO.getById(id)).thenReturn(category);

        Category result = categoryService.getCategoryById(id);

        assertThat(result.getCategoryName()).isEqualTo("category name");
        assertThat(result.getSlug()).isEqualTo("category-name");
    }

    @Test
    public void whenGetCategoryByName_returnTrue() {
        Mockito.when(categoryDAO.getByName(any(String.class))).thenReturn(category);

        Category result = categoryService.getCategoryByName("category name");

        assertThat(result.getSlug()).isEqualTo("category-name");
    }

    @Test
    public void createItem_returnTrue() {
        Mockito.when(categoryDAO.saveCategory(any(Category.class))).thenReturn(1);
        // Then
        int result = categoryService.saveCategory(category);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void deleteItem_returnTrue() {
        Mockito.when(categoryDAO.deleteCategory(id)).thenReturn(1);
        // Then
        int result = categoryService.deleteCategory(id);

        assertThat(result).isEqualTo(1);
    }

}
