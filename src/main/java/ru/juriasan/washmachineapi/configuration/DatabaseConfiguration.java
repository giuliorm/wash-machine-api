package ru.juriasan.washmachineapi.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Objects;

@Configuration
public class DatabaseConfiguration {

//  private static final String HOST_IS_NULL = "Host property is null or not found in the properties file.";
//  private static final String PORT_IS_NULL = "Port property is null or not found in the properties file.";
//  private static final String DATABASE_NAME_IS_NULL =
//      "Database name property is null or not found in the properties file";
//
//  @Value("${spring.data.mongodb.host}")
//  private String host;
//
//  @Value("${spring.data.mongodb.port}")
//  private String port;
//
//  @Bean
//  public Mongo mongoClient() {
//
//    if ( host == null || Objects.equals("", host)) {
//      throw new BeanCreationException(HOST_IS_NULL);
//    }
//    if ( port == null ) {
//      throw new BeanCreationException(PORT_IS_NULL);
//    }
//    int portValue = Integer.parseInt(port);
//    return new MongoClient(host, portValue);
//  }
}
