package com.example.demo.dao.daointerface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Review;

@Repository
public interface ReviewJPA extends JpaRepository<Review, Integer> {

}
