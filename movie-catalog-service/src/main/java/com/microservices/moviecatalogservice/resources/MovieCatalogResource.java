package com.microservices.moviecatalogservice.resources;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Console;
import java.util.Arrays;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.moviecatalogservice.models.CatalogItem;
import com.microservices.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//get all rated movieids
		List<Rating> ratingsList= Arrays.asList(
				new Rating("1234", 4),
				new Rating("1235", 3),
				new Rating("1236", 2),
				new Rating("1237", 5)
				);
		
		// for each movieid, call movie info service and get details
	return	ratingsList.stream().map(rating -> new CatalogItem( "Athadu", "Test", rating.getRating())).collect(Collectors.toList());

		//put them all together
//		return Collections.singletonList(
//				 new CatalogItem( "Athadu", "Test", 4));
	}

}
