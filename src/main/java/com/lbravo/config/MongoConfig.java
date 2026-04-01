package com.lbravo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoClientConfiguration {
  @Value("${spring.mongodb.uri}")
  private String mongoUri;

  @Value("${spring.mongodb.database}")
  private String databaseName;

  @Override
  protected String getDatabaseName() {
    return databaseName;
  }

  @Override
  public MongoClient mongoClient() {
    return MongoClients.create(mongoUri);
  }
}
