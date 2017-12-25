package ru.juriasan.washmachineapi.service;

import java.util.List;
import ru.juriasan.washmachineapi.domain.WashMachine;

public interface WashMachineService {

  List<WashMachine> findAll();
  WashMachine findByModelName(String modelName);
  WashMachine save(WashMachine machine);
  void remove(WashMachine machine);
}
