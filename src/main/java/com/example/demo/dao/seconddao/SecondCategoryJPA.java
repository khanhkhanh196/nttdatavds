package com.example.demo.dao.seconddao;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecondCategoryJPA extends JpaRepository<Category, Integer> {
    public Optional<Category> findByCategoryName(String name);
}
