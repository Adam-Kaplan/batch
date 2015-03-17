package com.test.batch;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.test.cassandra.CassandraConfiguration;
import com.test.cassandra.CassandraDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class, classes={CassandraConfiguration.class})
public class ApplicationIT {
	
	@Autowired
	private CassandraDAO cassandraDAO;

	@SuppressWarnings("unchecked")
	@Test
	public void testHappyPath() throws Exception {
		// before execution assertion
		assertThat(
			"There are no items in the database.",
			this.getCassandraDAO().readAll(),
			empty()
		);
		
		// run application
		Application.main( new String[0] );
		
		// after execution assertion 
		assertThat(
			"The return from web server has been stored in the database.",
			this.getCassandraDAO().readAll(),
			containsInAnyOrder(
				allOf(
					hasProperty("key", equalTo("value")),
					hasProperty("id", notNullValue())
				),
				allOf(
					hasProperty("key", equalTo("value2")),
					hasProperty("id", notNullValue())
				)
			)
		);
		
	}

	public CassandraDAO getCassandraDAO() {
		return cassandraDAO;
	}
	public void setCassandraDAO(CassandraDAO cassandraDAO) {
		this.cassandraDAO = cassandraDAO;
	}
}
