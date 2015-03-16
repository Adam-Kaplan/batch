package com.test.cassandra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Configuration
@PropertySource("classpath:conf.properties")
public class CassandraConfiguration {

	
	@Bean(destroyMethod="close")
	public Cluster cassandraCluster() {
		
		return Cluster.builder()
				.addContactPoint("127.0.0.1")
			.build();
	}
	
	@Bean
	public Session cassandraSession(Cluster cluster) {
		return cluster.connect("simple");
	}
}
