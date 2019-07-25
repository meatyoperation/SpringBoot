package com.SpringBoot.resources;


//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.SpringBoot.models.CatalogItem;
import com.SpringBoot.models.Movie;
//import Rating;
import com.SpringBoot.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
	
	@Autowired
	private RestTemplate restTemplate;
	
    @RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
    	
    	//UserRatings ratings = new UserRatings();
    	UserRatings ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/" + userId, UserRatings.class);
    	return ratings.getRatings().stream().map(rating -> {
    		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getName(), Movie.class);
    		return new CatalogItem(movie.getName(),"desc",rating.getRating());
    	})
    	.collect(Collectors.toList());
	}

}
