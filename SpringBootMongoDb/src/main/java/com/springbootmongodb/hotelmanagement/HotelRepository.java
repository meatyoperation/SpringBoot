package com.springbootmongodb.hotelmanagement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String>, QuerydslPredicateExecutor<Hotel>{

	Optional<Hotel> findById(String id);
	List<Hotel> findByPricePerNightLessThan(int max);
	
	
}
