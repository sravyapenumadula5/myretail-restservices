package com.myretail.webservices.restservices.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("DatabaseSequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSequenceDocument {

	@Id
	private String id;
	private Integer sequenceNumber;

}
