package com.myretail.webservices.restservices.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.webservices.restservices.dao.ProductDetailsDocument;
import com.myretail.webservices.restservices.exception.MyRetailException;
import com.myretail.webservices.restservices.exception.ResourceNotFoundException;
import com.myretail.webservices.restservices.exception.SequenceGenerationException;
import com.myretail.webservices.restservices.helper.MyRetailServiceHelper;
import com.myretail.webservices.restservices.model.Product;
import com.myretail.webservices.restservices.repository.ProductDetailsRepository;
import com.myretail.webservices.restservices.service.ProductService;
import com.myretail.webservices.restservices.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	@Autowired
	private MyRetailServiceHelper myRetailServiceHelper;

	@Value("${product.details.url}")
	private String redskyUrl;

	@Override
	public String getProductDetailService(Integer productId) {

		log.info("Product Details service - Getting Product details for product id = {}", productId);

		String responseString = null;
		ProductDetailsDocument productDetailsDocument = null;
		Product response = null;
		try {
			productDetailsDocument = productDetailsRepository.findById(productId).get();
			if (productDetailsDocument != null) {
				response = myRetailServiceHelper.prepareGetProductResponse(productDetailsDocument);
				responseString = objectMapper.writeValueAsString(response);
			}
		} catch (JsonProcessingException e) {
			log.error("Processing Json Exception while getting product details ", e);
			throw new MyRetailException("Exception while converting to json string");
		} catch (NoSuchElementException e) {
			log.error("Resource not found  Exception while getting product details ", e);
			throw new ResourceNotFoundException("No Product details found for the given id");
		} catch (Exception e) {
			log.error("Unknown Exception while getting product details ", e);
			throw new MyRetailException("Unknown Exception");
		}

		return responseString;
	}

	@Override
	public String createProductDetailsService(Product product) {

		ProductDetailsDocument productDetailsDocument = null;
		Product response = null;
		String responseString = null;
		try {
			productDetailsDocument = myRetailServiceHelper.prepareProductDocument(product);
			if (product.getProductId() != null) {
				productDetailsDocument.setId(product.getProductId());
			} else {
				productDetailsDocument.setId(sequenceGenerator.getSequenceNumber(ProductDetailsDocument.SEQUENCE_NAME));

			}
			productDetailsDocument = productDetailsRepository.save(productDetailsDocument);
			if (productDetailsDocument != null) {
				response = myRetailServiceHelper.prepareGetProductResponse(productDetailsDocument);
				responseString = objectMapper.writeValueAsString(response);
			}
		} catch (SequenceGenerationException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unknown Exception while saving product details ", e);
			throw new MyRetailException("Unknown Exception while saving product details");

		}
		return responseString;
	}

}
