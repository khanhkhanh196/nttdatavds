package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.daointerface.ReviewDAO;
import com.example.demo.dao.daointerface.ReviewJPA;
import com.example.demo.entity.Review;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	@Autowired
	private ReviewJPA reviewJPA;

	@Override
	public void saveReview(Review review) {
		reviewJPA.save(review);
	}

}
