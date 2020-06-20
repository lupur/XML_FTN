package com.ftnxml.customerexperience.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.customerexperience.enums.ReviewStatus;
import com.ftnxml.customerexperience.model.Review;
import com.ftnxml.customerexperience.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public List<Review> getReviewsByAuthor(Long authorId) {
		return reviewRepository.findByAuthorId(authorId);
	}
	
	@Override
	public List<Review> getReviewsByVehicle(Long vehicleId) {
		return reviewRepository.findByVehicleId(vehicleId);
	}

	@Override
	public Review getReview(Long id) {
		try {
			Review r = reviewRepository.findById(id).get();
			return r;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeReview(Long id) {
		try {
			Review r = reviewRepository.findById(id).get();
			reviewRepository.delete(r);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addReview(Review newReview) {
		if (newReview == null) {
			return false;
		}

		try {
			reviewRepository.save(newReview);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
    public boolean changeReviewStatus(Long reviewId, ReviewStatus newStatus) {
        Review r = getReview(reviewId);
        
        if (r == null) {
            return false;
        }

        r.setStatus(newStatus);
        reviewRepository.save(r);
        return true;
    }
}