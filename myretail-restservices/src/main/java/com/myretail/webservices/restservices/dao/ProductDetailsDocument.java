package com.myretail.webservices.restservices.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("ProductDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDocument {

	@Transient
	public static final String SEQUENCE_NAME = "Product_Detail_Seq";

	@Id
	private Integer id;
	private String name;
	private String description;
	private ProductPriceDocument priceDetails;

}
