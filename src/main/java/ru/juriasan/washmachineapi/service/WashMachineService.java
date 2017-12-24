package ru.juriasan.washmachineapi.service;

import ru.juriasan.washmachineapi.domain.WashMachine;

import java.util.List;

public interface WashMachineService {

  List<WashMachine> findAll();
  WashMachine findByModelName(String modelName);

}
