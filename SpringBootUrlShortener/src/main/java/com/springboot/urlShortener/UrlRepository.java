package com.springboot.urlShortener;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlObject, String> {

	public UrlObject findByshortid(String shortid);
}
