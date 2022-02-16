package com.myretail.webservices.restservices.controller;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.myretail.webservices.restservices.service.impl.ProductServiceImpl;

@WebMvcTest
public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@MockBean
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	private MockMvc mockMvc;
	
	

}
