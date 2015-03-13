package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.cassandra.CassandraDAO;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run( Application.class , args );
	}

	@Autowired
	private CassandraDAO cassandraDAO;
	
	@Override
	public void run(String... args) throws Exception {
		cassandraDAO.readAll().forEach(item -> {
			
			System.out.println( String.format("Cassandra contains: %s", item) );
			
		});
	}

	public CassandraDAO getCassandraDAO() {
		return cassandraDAO;
	}
	public void setCassandraDAO(CassandraDAO cassandraDAO) {
		this.cassandraDAO = cassandraDAO;
	}
}
