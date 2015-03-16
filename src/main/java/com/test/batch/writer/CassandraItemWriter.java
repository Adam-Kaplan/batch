package com.test.batch.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.cassandra.CassandraDAO;
import com.test.cassandra.entity.CassandraItem;

@Component
public class CassandraItemWriter implements ItemWriter<CassandraItem> {
	private static final Logger logger = Logger.getLogger(CassandraItemWriter.class);
	
	@Autowired
	public CassandraDAO cassandraDAO;

	@Override
	public void write(List<? extends CassandraItem> items) throws Exception {
		items.forEach( item -> {
			logger.debug( "Saving to Cassandra : " + item );
			
			cassandraDAO.write(item);
		});
	}

	public CassandraDAO getCassandraDAO() {
		return cassandraDAO;
	}
	public void setCassandraDAO(CassandraDAO cassandraDAO) {
		this.cassandraDAO = cassandraDAO;
	}

}
