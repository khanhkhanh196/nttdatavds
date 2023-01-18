package com.example.demo.seconddb.dao;

import com.example.demo.entity.Category;
import com.example.demo.seconddb.entity.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecondCategoryJPA extends JpaRepository<SecondCategory, Integer> {
    public Optional<SecondCategory> findByCategoryName(String name);
}
