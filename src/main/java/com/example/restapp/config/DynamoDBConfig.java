package com.example.restapp.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.restapp.repo")
public class DynamoDBConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String dBEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String accessKey;

	@Value("${amazon.aws.secretkey}")
	private String secretKey;

	@Value("${amazon.dynamodb.region}")
    private String amazonDynamoDBRegion;

	@Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dBEndpoint, amazonDynamoDBRegion))
                .build();
    }

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	/*
	 swagger
	 unit tests
	 */
}
