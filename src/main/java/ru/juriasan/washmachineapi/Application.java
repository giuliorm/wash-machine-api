package ru.juriasan.washmachineapi;

import com.mongodb.MongoClient;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.juriasan.washmachineapi.controllers.exception.InvalidParameterException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

//  @Autowired
//  private MongoClient mongoClient;
//
//  @Value("${spring.data.mongodb.database}")
//  private String dbName;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
