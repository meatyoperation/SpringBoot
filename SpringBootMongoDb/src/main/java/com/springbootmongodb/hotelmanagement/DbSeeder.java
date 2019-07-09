package com.springbootmongodb.hotelmanagement;

import java.util.Arrays;
import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbSeeder implements CommandLineRunner {
	
	private HotelRepository hotelRepository;

	
	public DbSeeder(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		Hotel marriot = new Hotel(
				"Marriot",
				150,
				new Address("Bangalore", "India"),
				Arrays.asList(
						new Review("Jack",8,false),
						new Review("Anna",7,true)
						)
				);
		Hotel sevenska = new Hotel(
				"SevenSka",
				90,
				new Address("Bangalore", "India"),
				Arrays.asList(
						new Review("James",6,false)
						)
				);
		Hotel ibis = new Hotel(
				"Ibis",
				100,
				new Address("Delhi", "India"),
				Arrays.asList()
				);
	
		hotelRepository.deleteAll();
		List<Hotel> hotels = Arrays.asList(marriot,sevenska,ibis);
		this.hotelRepository.saveAll(hotels);
		//this.hotelRepository.insert(marriot);
		//this.hotelRepository.insert(sevenska);
		
	}

}
