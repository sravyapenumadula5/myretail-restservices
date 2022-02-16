package com.myretail.webservices.restservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.webservices.restservices.model.Product;
import com.myretail.webservices.restservices.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products/{productId}")
	public ResponseEntity<String> getProductDetailsWithProductId(@PathVariable Integer productId) {
		log.info("Getting product details for product Id = {}", productId);
		String response = productService.getProductDetailService(productId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> createProductDetailsWithProductId(@RequestBody Product product) {
		log.info("Create product details service start");
		String response = productService.createProductDetailsService(product);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

}
