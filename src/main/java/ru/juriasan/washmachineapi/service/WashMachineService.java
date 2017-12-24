package ru.juriasan.washmachineapi.service;

import org.springframework.stereotype.Service;
import ru.juriasan.washmachineapi.domain.WashMachine;

import java.util.List;

public interface WashMachineService {

  public List<WashMachine> findAll();
  public WashMachine findByModelName(String modelName);
  public WashMachine save(WashMachine machine);
  public void remove(WashMachine machine);
}
