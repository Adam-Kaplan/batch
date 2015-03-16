package com.test.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.batch.processor.RESTItemToCassandraItemProcessor;
import com.test.batch.reader.RESTItemReader;
import com.test.batch.writer.CassandraItemWriter;
import com.test.cassandra.entity.CassandraItem;
import com.test.jaxrs.entity.RESTItem;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Bean
	public Job testJob(JobBuilderFactory jobs, Step testStep) {
		
		return jobs.get("testJob")
				.incrementer( new RunIdIncrementer() )
				.flow( testStep )
				.end()
				.build();
	}
	
	@Bean
	public Step testStep(
			StepBuilderFactory stepBuilderFactory, 
			RESTItemReader restItemReader, 
			RESTItemToCassandraItemProcessor restItemToCassandraItemProcessor, 
			CassandraItemWriter cassandraItemWriter) {
		
		return stepBuilderFactory.get("testStep")
				.<RESTItem, CassandraItem> chunk(1)
				.reader(restItemReader)
				.processor(restItemToCassandraItemProcessor)
				.writer(cassandraItemWriter)
				.build();
	}
}
