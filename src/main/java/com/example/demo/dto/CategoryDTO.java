package com.example.demo.dto;

import com.example.demo.entity.Category;
import com.example.demo.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

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
        String slug = StringUtils.toSlug(this.categoryName);
        category.setSlug(slug);
        return category;
    }
}
