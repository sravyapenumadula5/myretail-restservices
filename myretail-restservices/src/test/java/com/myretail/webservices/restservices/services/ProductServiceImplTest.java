package com.myretail.webservices.restservices.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.webservices.restservices.dao.ProductDetailsDocument;
import com.myretail.webservices.restservices.exception.MyRetailException;
import com.myretail.webservices.restservices.exception.ResourceNotFoundException;
import com.myretail.webservices.restservices.helper.MyRetailServiceHelper;
import com.myretail.webservices.restservices.model.Price;
import com.myretail.webservices.restservices.model.Product;
import com.myretail.webservices.restservices.repository.ProductDetailsRepository;
import com.myretail.webservices.restservices.service.impl.ProductServiceImpl;
import com.myretail.webservices.restservices.service.impl.SequenceGeneratorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	private ProductDetailsRepository productDetailsRepository;

	@Mock
	private MyRetailServiceHelper myRetailServiceHelper;

	@Mock
	private SequenceGeneratorServiceImpl sequenceGeneratorService;

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	ProductDetailsDocument productDetailsDocument;

	@Mock
	Product product;

	@Mock
	ObjectMapper mapper;

	@Test
	void shouldFetchProductDetailWithProductId() throws JsonProcessingException {
		Integer productId = 2;

		when(productDetailsRepository.findById(productId)).thenReturn(Optional.of(productDetailsDocument));
		when(myRetailServiceHelper.prepareGetProductResponse(productDetailsDocument)).thenReturn(product);
		when(mapper.writeValueAsString(product)).thenReturn(
				"{\"_id\":2,\"name\":\"Test Product\",\"description\":\"Test Product\",\"priceDetails\":{\"price\":\"100\",\"currency\":\"USD\"},\"_class\":\"com.myretail.webservices.restservices.dao.ProductDetailsDocument\"}");
		productService.getProductDetailService(productId);

		verify(productDetailsRepository).findById(productId);
	}

	@Test
	void shouldThrowExceptionWhenProductNotFound() {
		Integer productId = 10;
		when(productDetailsRepository.findById(productId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> productService.getProductDetailService(productId));

	}

	@Test
	void shouldCreateProductDetails() {
		var productDetails = getProductDetailsForCreate();

		var productId = 100;
		when(myRetailServiceHelper.prepareProductDocument(productDetails)).thenReturn(productDetailsDocument);
		when(sequenceGeneratorService.getSequenceNumber(ProductDetailsDocument.SEQUENCE_NAME)).thenReturn(productId);
		productService.createProductDetailsService(productDetails);

		verify(productDetailsRepository).save(productDetailsDocument);
	}

	/*
	 * @Test void shouldThrowDuplicateExceptionWhenCreateProductDetails() { var
	 * productDetails = getProductDetailsForCreate();
	 * 
	 * var productId = 1;
	 * when(myRetailServiceHelper.prepareProductDocument(productDetails)).thenReturn
	 * (productDetailsDocument);
	 * when(sequenceGeneratorService.getSequenceNumber(ProductDetailsDocument.
	 * SEQUENCE_NAME)).thenReturn(productId);
	 * when(productDetailsRepository.save(productDetailsDocument)).thenThrow(
	 * InvalidDataAccessResourceUsageException.class);
	 * productService.createProductDetailsService(productDetails);
	 * 
	 * assertThrows(MyRetailException.class, () ->
	 * productService.createProductDetailsService(productDetails)); }
	 */

	@Test
	void shouldUpdateProductDetails() throws JsonProcessingException {
		var productDetails = getProductDetailsForUpdate();
		when(myRetailServiceHelper.prepareProductDocument(productDetails)).thenReturn(productDetailsDocument);
		when(productDetailsRepository.save(productDetailsDocument)).thenReturn(productDetailsDocument);
		when(myRetailServiceHelper.prepareGetProductResponse(productDetailsDocument)).thenReturn(productDetails);
		productService.createProductDetailsService(productDetails);
		verify(productDetailsRepository).save(productDetailsDocument);
	}

	private Product getProductDetailsForCreate() {
		Product product = new Product();
		product.setDescription("Running Test");
		product.setProductName("Test Product");
		Price price = new Price(BigDecimal.valueOf(100.00), "USD");
		product.setPriceDetails(price);
		return product;
	}

	private Product getProductDetailsForUpdate() {
		Product product = new Product();
		product.setDescription("Running Test");
		product.setProductName("Test Product");
		product.setProductId(1);
		Price price = new Price(BigDecimal.valueOf(100.00), "USD");
		product.setPriceDetails(price);
		return product;
	}

}
