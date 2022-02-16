package com.myretail.webservices.restservices.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.stereotype.Service;

import com.myretail.webservices.restservices.dao.DatabaseSequenceDocument;
import com.myretail.webservices.restservices.exception.SequenceGenerationException;
import com.myretail.webservices.restservices.service.SequenceGeneratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public Integer getSequenceNumber(String sequenceName) {

		log.info("Generating sequence number for product");

		DatabaseSequenceDocument counter;
		try {
			Query query = new Query(Criteria.where("id").is(sequenceName));
			Update update = new Update().inc("sequenceNumber", 1);
			counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
					DatabaseSequenceDocument.class);
		} catch (Exception e) {
			log.error("Exception while generating sequence number", e);
			throw new SequenceGenerationException("Exception while generating sequence number");
		}
		return !Objects.isNull(counter) ? counter.getSequenceNumber() : 1;
	}

}
