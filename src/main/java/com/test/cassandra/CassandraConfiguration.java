package com.test.cassandra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Configuration
public class CassandraConfiguration {

	
	@Bean(destroyMethod="close")
	public Cluster cassandraCluster() {
		return Cluster.builder()
				.addContactPoint("127.0.0.1")
				.withPort(7000)
			.build();
	}
	
	@Bean
	public Session cassandraSession(Cluster cluster) {
		return cluster.connect("simple");
	}
}
