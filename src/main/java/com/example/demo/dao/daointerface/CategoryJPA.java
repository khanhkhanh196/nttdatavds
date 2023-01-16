package com.example.demo.dao.daointerface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryJPA extends JpaRepository<Category, Integer> {
    public Optional<Category> findByCategoryName(String name);
}
