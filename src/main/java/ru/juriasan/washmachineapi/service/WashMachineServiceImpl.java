package ru.juriasan.washmachineapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

import java.util.List;

@Service
public class WashMachineServiceImpl implements WashMachineService {

  @Autowired
  private WashMachineRepository repository;

  @Override
  public List<WashMachine> findAll() {
    return repository.findAll();
  }

  @Override
  public WashMachine findByModelName(String modelName) {
    if ( modelName == null ) {
      return null;
    }
    return repository.findByModelName(modelName);
  }

  @Override
  public WashMachine save(WashMachine machine) {
    return repository.save(machine);
  }

  @Override
  public void remove(WashMachine machine) {
    repository.delete(machine);
  }
}
