package com.ftnxml.customerexperience.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftnxml.customerexperience.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findByAuthorId(Long authorId);
	
	List<Review> findByVehicleId(Long vehicleId);
}
