package com.example.demo.dao;

import com.example.demo.dao.daointerface.CategoryJPA;
import com.example.demo.entity.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
public class CategoryDaoImplTest {
    @InjectMocks
    CategoryDAOImpl categoryDAO;

    @Mock
    CategoryJPA categoryJPA;

    int id = 1;

    Category category = new Category(id, "category name", "category-name");

    Optional<Category> categoryOptional = Optional.of(category);

    @Before
    public void setUp() {

    }

    @Test
    public void whenGetById_returnTrue() {
        Mockito.when(categoryJPA.findById(id)).thenReturn(categoryOptional);

        Category result = categoryDAO.getById(id);

        assertThat(result.getCategoryName()).isEqualTo("category name");
        assertThat(result.getSlug()).isEqualTo("category-name");
    }

    @Test
    public void whenGetByName_returnTrue() {
        Mockito.when(categoryJPA.findByCategoryName(any(String.class))).thenReturn(categoryOptional);

        Category result = categoryDAO.getByName("category name");

        assertThat(result.getSlug()).isEqualTo("category-name");
    }

    @Test
    public void whenSaveCategory_returnTrue() {
        Mockito.when(categoryJPA.save(any(Category.class))).thenReturn(category);
        // Then
        int result = categoryDAO.saveCategory(category);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void whenDeleteCategory_returnTrue() {
        Mockito.when(categoryJPA.findById(id)).thenReturn(categoryOptional);
        doNothing().when(categoryJPA).deleteById(id);
        // Then
        int result = categoryDAO.deleteCategory(id);

        assertThat(result).isEqualTo(1);
    }
}
