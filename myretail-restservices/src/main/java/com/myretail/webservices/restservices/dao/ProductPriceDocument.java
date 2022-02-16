package com.myretail.webservices.restservices.dao;

import java.math.BigDecimal;

import com.myretail.webservices.restservices.model.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceDocument {

	private BigDecimal price;
	private String currency;

	public ProductPriceDocument(Price price) {
		super();
		this.price = price.getPrice();
		this.currency = price.getCurrency();
	}

}
