package com.ftnxml.customerexperience.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.customerexperience.service.ReviewService;
import com.ftnxml.customerexperience.dto.ReviewDto;
import com.ftnxml.customerexperience.enums.ReviewStatus;
import com.ftnxml.customerexperience.model.Review;;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getReviews(){
		List<ReviewDto> reviews = reviewService.getAllReviews().stream()
				.map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
		return ResponseEntity.ok(reviews);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReview(@PathVariable Long id) {
        Review review = reviewService.getReview(id);
        if (review == null)
            ResponseEntity.notFound().build();
        ReviewDto md = modelMapper.map(review, ReviewDto.class);
        return ResponseEntity.ok(md);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeReview(@PathVariable Long id) {
        if (reviewService.removeReview(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
    
    @PutMapping(value = "/{id}/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity approveReview(@PathVariable Long id) {
        if (reviewService.changeReviewStatus(id, ReviewStatus.APPROVED))
            return ResponseEntity.ok("Review approved.");
        else
            return ResponseEntity.badRequest().body("No review with given id");
    }

    @PutMapping(value = "/{id}/reject", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity rejectReview(@PathVariable Long id) {
        if (reviewService.changeReviewStatus(id, ReviewStatus.REJECTED))
            return ResponseEntity.ok("User blocked.");
        else
            return ResponseEntity.badRequest().body("No review with given id");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addReview(@RequestBody ReviewDto newReview) {
        if (newReview == null || newReview.getAuthorName().isEmpty() || newReview.getComment().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Review review = new Review();
        review.setAuthorId(newReview.getAuthorId());
        review.setAuthorName(newReview.getAuthorName());
        review.setComment(newReview.getComment());
        review.setCreationDate(newReview.getCreationDate());
        review.setVehicleOrderId(newReview.getVehicleOrderId());
        review.setRating(newReview.getRating());
        review.setStatus(ReviewStatus.PENDING);

        if (reviewService.addReview(review))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
