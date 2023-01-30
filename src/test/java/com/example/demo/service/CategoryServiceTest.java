package com.example.demo.service;

import com.example.demo.repository.CategoryRepository;
import com.example.demo.entity.Category;
import com.example.demo.service.serviceinterface.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@RunWith(SpringRunner.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    int id = 1;

    Category category = new Category(id, "category name", "category-name");

    List<Category> categories = new ArrayList<>();

    @Before
    public void setUp() {

    }

    @Test
    public void whenGetCategoryById_returnTrue() {
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(category));

        Category result = categoryService.getCategoryById(id);

        assertThat(result.getCategoryName()).isEqualTo("category name");
        assertThat(result.getSlug()).isEqualTo("category-name");
    }

    @Test
    public void whenGetCategoryByName_returnTrue() {
        categories.add(category);
        Mockito.when(categoryRepository.findByCategoryNameContaining(any(String.class))).thenReturn(categories);

        List<Category> result = categoryService.getCategoryByName("category name");
        assertThat(result.size()).isEqualTo(1);

        assertThat(result.get(0).getSlug()).isEqualTo("category-name");
    }

    @Test
    public void createItem_returnTrue() {
        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(category);
        // Then
        int result = categoryService.saveCategory(category);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void deleteItem_returnTrue() {
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(id);
        // Then
        int result = categoryService.deleteCategory(id);

        assertThat(result).isEqualTo(1);
    }

}
