package com.example.demo.dto;

import com.example.demo.entity.Category;
import com.example.demo.util.BaseUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @JsonProperty("category_name")
    @NotBlank
    private String categoryName;
    private String slug;

    public Category convertToEntity() {
        ModelMapper modelMapper = new ModelMapper();
        Category category = modelMapper.map(this, Category.class);
        String slug = BaseUtils.toSlug(this.categoryName);
        category.setSlug(slug);
        return category;
    }
}
