package com.test.batch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.test.cassandra.CassandraDAO;

@SpringBootApplication
@ComponentScan(basePackages={"com.test.cassandra","com.test.jaxrs","com.test.batch"})
public class Application implements CommandLineRunner {
	private static final Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		logger.debug( "Start Application." );
		
		ConfigurableApplicationContext context = SpringApplication.run( Application.class , args );
		
		context.close();
		
		logger.debug( "End Application." );
	}

	@Autowired
	private CassandraDAO cassandraDAO;

	@Override
	public void run(String... args) throws Exception {}

	public CassandraDAO getCassandraDAO() {
		return cassandraDAO;
	}
	public void setCassandraDAO(CassandraDAO cassandraDAO) {
		this.cassandraDAO = cassandraDAO;
	}
}
