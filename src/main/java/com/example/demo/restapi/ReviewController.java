package com.example.demo.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Review;
import com.example.demo.service.serviceinterface.ReviewService;

@RestController
@RequestMapping("/rest")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@PostMapping("/reviews")
	public void addNewReview(@Validated @RequestBody Review review) {
		review.setId(0);
		reviewService.saveReview(review);
	}
}
