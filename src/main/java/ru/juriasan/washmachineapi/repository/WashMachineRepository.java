package ru.juriasan.washmachineapi.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.juriasan.washmachineapi.domain.WashMachine;

public interface WashMachineRepository extends MongoRepository<WashMachine, String> {

  WashMachine findByModelName(String modelName);
  WashMachine findById(String id);
}
