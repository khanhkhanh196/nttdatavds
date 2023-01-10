package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daointerface.ReviewDAO;
import com.example.demo.entity.Review;
import com.example.demo.service.serviceinterface.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDAO reviewDao;
	
	@Override
	public void saveReview(Review review) {
		reviewDao.saveReview(review);
	}

}
