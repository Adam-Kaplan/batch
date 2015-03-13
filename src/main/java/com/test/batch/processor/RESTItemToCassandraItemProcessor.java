package com.test.batch.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.test.cassandra.entity.CassandraItem;
import com.test.jaxrs.entity.RESTItem;

@Component
public class RESTItemToCassandraItemProcessor implements ItemProcessor<RESTItem, CassandraItem> {

	@Override
	public CassandraItem process(RESTItem item) throws Exception {
		CassandraItem result = new CassandraItem();
		
		result.setId( UUID.randomUUID() );
		result.setKey( item.getKey() );
		
		return result;
	}

}
