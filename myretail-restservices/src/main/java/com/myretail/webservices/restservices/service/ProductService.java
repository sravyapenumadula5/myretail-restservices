package com.myretail.webservices.restservices.service;

import com.myretail.webservices.restservices.model.Product;

public interface ProductService {
	
	public String getProductDetailService(Integer productId);

	public String createProductDetailsService(Product product);

}
