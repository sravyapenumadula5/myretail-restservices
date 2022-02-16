package com.myretail.webservices.restservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myretail.webservices.restservices.dao.ProductPriceDocument;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPriceDocument, Integer> {
	
}
