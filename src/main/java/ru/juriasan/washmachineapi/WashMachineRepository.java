package ru.juriasan.washmachineapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WashMachineRepository extends MongoRepository<WashMachine, String> {

    WashMachine findByFirstName(String firstName);
    List<WashMachine> findByLastName(String lastName);

}
