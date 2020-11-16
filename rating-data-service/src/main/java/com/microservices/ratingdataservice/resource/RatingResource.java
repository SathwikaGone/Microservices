package com.microservices.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingdataservice.model.Rating;
import com.microservices.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/rating")
public class RatingResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
		List<Rating> ratingsList= Arrays.asList(
				new Rating("100", 4),
				new Rating("200", 3),
				new Rating("300", 2),
				new Rating("400", 5)
				); 
		 UserRating userRating = new UserRating();
				userRating.setUserRatings(ratingsList);
		 return userRating;
	}

}
