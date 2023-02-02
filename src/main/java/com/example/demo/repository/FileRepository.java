package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.File;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    public static final String FIND_IMAGE_URL =
            "SELECT * " +
                    "FROM file " +
                    "WHERE file_id IN (" +
                    "SELECT file_references_product_id " +
                    "FROM product_file WHERE product_references_file_id = :productId)";

    @Query(value = FIND_IMAGE_URL, nativeQuery = true)
    public List<File> getFilesByProductId(@Param("productId") int productId);
}
