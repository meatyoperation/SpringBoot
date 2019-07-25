package com.SpringBoot.resources;

import com.SpringBoot.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResources {
	
	@RequestMapping("/{movieId}")
	public Movie getMovie(@PathVariable("movieId") String id) {
		return new Movie(id, "Test name");
	}

}
