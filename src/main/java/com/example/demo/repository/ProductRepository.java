package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query(value = "SELECT * FROM product p " +
            "WHERE p.product_id IN " +
            "(SELECT product_references_category_id product_id " +
            "FROM category_product cp " +
            "JOIN category c " +
            "ON cp.product_references_category_id = c.category_id " +
            "WHERE category_name like %:name%)", nativeQuery = true)
    public List<Product> getAllProductByCategoryName(@Param("name") String name);

    List<Product> findByProductNameContaining(String productName);
}
