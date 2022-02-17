package com.myretail.webservices.restservices.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.myretail.webservices.restservices.exception.ResourceNotFoundException;
import com.myretail.webservices.restservices.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;

	@Test
	void getProductWithIdTest() throws Exception {
		Integer productId = 10;

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		String product = "{\"productId\":10,\"productName\":\"Test Product\",\"priceDetails\":{\"price\":100,\"currency\":\"INR\"},\"description\":\"Test Product\"}";
		given(productService.getProductDetailService(productId)).willReturn(product);

		mockMvc.perform(get("/api/products/{productId}", productId).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	void getProductWithIdTestThrowExc() throws Exception {
		Integer productId = 1000;

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		given(productService.getProductDetailService(productId)).willThrow(ResourceNotFoundException.class);

		mockMvc.perform(get("/api/products/{productId}", productId).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is4xxClientError());

	}

	@Test
	void createProductTest() throws Exception {

	}

}
