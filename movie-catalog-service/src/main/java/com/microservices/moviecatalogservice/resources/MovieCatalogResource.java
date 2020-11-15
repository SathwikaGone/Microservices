package com.microservices.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.moviecatalogservice.models.CatalogItem;
import com.microservices.moviecatalogservice.models.Movie;
import com.microservices.moviecatalogservice.models.UserRating;
import com.netflix.discovery.DiscoveryClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	Can get the all the instances and use whenever it is required
//  It has list of instances
//	@Autowired
//	private DiscoveryClient discoveryClient;
		
//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
			
		//get all rated movieIds
		UserRating ratingsList= restTemplate.getForObject("http://rating-data-service/rating/users/"+ userId, UserRating.class);
		
		return	ratingsList.getUserRatings().stream().map(rating -> {
			// for each movieId, call movie info service and get details
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId()   , Movie.class);
			
			//put them all together
			return new CatalogItem( movie.getName(), "desc", rating.getRating());

		})
		.collect(Collectors.toList());
	}
}


//return Collections.singletonList(
//new CatalogItem( "Athadu", "Test", 4));

// Web client 
//Movie movie =webClientBuilder.build()
//		.get()
//		.uri("http://localhost:8082/movies/" + rating.getMovieId())
//		.retrieve()
//		.bodyToMono(Movie.class)
//		.block();	



