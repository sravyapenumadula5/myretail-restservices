package com.myretail.webservices.restservices.helper;

import org.springframework.stereotype.Component;

import com.myretail.webservices.restservices.dao.ProductDetailsDocument;
import com.myretail.webservices.restservices.dao.ProductPriceDocument;
import com.myretail.webservices.restservices.model.Price;
import com.myretail.webservices.restservices.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyRetailServiceHelper {

	public Product prepareGetProductResponse(ProductDetailsDocument productDetails) {

		log.info("Creating get product response");

		Price priceDetails = null;
		Product response = null;
		ProductPriceDocument productPriceDocument = null;

		response = new Product();
		response.setDescription(productDetails.getDescription());
		response.setProductId(productDetails.getId());
		response.setProductName(productDetails.getName());
		priceDetails = new Price();
		productPriceDocument = productDetails.getPriceDetails();

		if (null != productPriceDocument) {
			priceDetails.setCurrency(productPriceDocument.getCurrency());
			priceDetails.setPrice(productPriceDocument.getPrice());
			response.setPriceDetails(priceDetails);
		}

		return response;
	}

	public ProductDetailsDocument prepareProductDocument(Product product) {
		ProductDetailsDocument productDetailsDocument = new ProductDetailsDocument();
		ProductPriceDocument productPriceDocument = new ProductPriceDocument();
		productPriceDocument.setCurrency(product.getPriceDetails().getCurrency());
		productPriceDocument.setPrice(product.getPriceDetails().getPrice());
		productDetailsDocument.setDescription(product.getDescription());
		productDetailsDocument.setName(product.getProductName());
		productDetailsDocument.setPriceDetails(productPriceDocument);
		return productDetailsDocument;
	}

}
