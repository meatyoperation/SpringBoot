package com.springbootmongodb.hotelmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	private HotelRepository hotelRepository;
    	
	public HotelController(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@RequestMapping("/all")
	public List<Hotel> getAll(){
		List<Hotel> hotels = this.hotelRepository.findAll();
		return hotels;
	}
	
	@PutMapping()
	public void insert(@RequestBody Hotel hotel) {
		this.hotelRepository.insert(hotel);
	}
	
	@PostMapping()
	public void update(@RequestBody Hotel hotel) {
		this.hotelRepository.save(hotel);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.hotelRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Hotel> getById(@PathVariable("id") String id) {
		Optional<Hotel> hotel = this.hotelRepository.findById(id);
	    return hotel;
	}
	
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getAllHotelsForPrice(@PathVariable("maxPrice") int maxPrice){
		List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);
		return hotels;
	}
	
	@GetMapping("/country/{country}")
	public List<Hotel> getByCountry(@PathVariable("country") String country){
		
		QHotel qHotel = new QHotel("hotel");
		BooleanExpression filterByCountry = qHotel.address.country.eq(country);
		
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
		return hotels;
	}
	
	@GetMapping("/recommended")
	public List<Hotel> getRecommended(){
		
		final int maxPrice =100;
		final int minRating = 7;
		QHotel qHotel = new QHotel("hotel");
		BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
		BooleanExpression filterByRating = qHotel.reviews.any().ratings.gt(minRating);
		
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));
		return hotels;
	}
}
