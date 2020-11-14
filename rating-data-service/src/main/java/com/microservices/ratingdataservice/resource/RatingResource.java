package com.microservices.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingdataservice.model.Rating;

@RestController
@RequestMapping("/rating")
public class RatingResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public List<Rating> getUserRating(@PathVariable("userId") String userId) {
		
		List<Rating> ratingsList= Arrays.asList(
				new Rating("1234", 4),
				new Rating("1235", 3),
				new Rating("1236", 2),
				new Rating("1237", 5)
				); 
		return ratingsList;
	}

}
