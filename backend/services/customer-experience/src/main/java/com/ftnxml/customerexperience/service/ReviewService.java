package com.ftnxml.customerexperience.service;

import java.util.List;

import com.ftnxml.customerexperience.enums.ReviewStatus;
import com.ftnxml.customerexperience.model.Review;

public interface ReviewService {
	List<Review> getAllReviews();

    List<Review> getReviewsByAuthor(Long authorId);
    
    List<Review> getReviewsByVehicle(Long vehicleId);
    
    Review getReview(Long id);

    boolean removeReview(Long id);

    boolean addReview(Review newReview);
    
    boolean changeReviewStatus(Long reviewId, ReviewStatus newStatus);
}
