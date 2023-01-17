package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDTO {
    @JsonProperty("category_name")
    private String categoryName;

    private String slug;
}
