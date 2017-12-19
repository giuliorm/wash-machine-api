package ru.juriasan.washmachineapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.DatabaseEntityNotFoundException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/state")
public class StateController extends BaseController {

  private static final String CANNOT_SET_STATE_FORMAT = "Cannot set state: %s";
  private static final String STATE_IS_NULL = "State parameter is null";

  @Autowired
  private WashMachineRepository repository;

  @RequestMapping("/state")
  public WashState getState(@RequestParam(name = "id") String machineId) {
    if ( machineId == null ) {
      throw new InvalidParameterException(MACHINE_ID_IS_NULL);
    }
    WashMachine machine = repository.findById(machineId);
    if ( machine == null) {
      throw new DatabaseEntityNotFoundException(String.format(MACHINE_IS_NOT_FOUND_FORMAT, machineId));
    }
    return machine.getState();
  }

  @RequestMapping("/state")
  public String setState(@RequestParam(name = "id") String machineId, @RequestParam(name = "state") WashState state) {
    if ( state == null ) {
      throw new InvalidParameterException(String.format(CANNOT_SET_STATE_FORMAT, STATE_IS_NULL));
    }
    if ( machineId == null ) {

    }
    WashMachine machine = repository.findById(machineId);
    if ( machine == null ) {
      return;
    }
    machine.setState(state);
    repository.save(machine);
  }

  @RequestMapping("/mode")
  public WashMode getCurrentWashMode(@RequestParam(name = "id") String machineId) {
    if ( machineId == null) {
      return null;
    }
    WashMachine machine = repository.findById(machineId);
    return machine != null ? machine.getCurrentWashMode() : null;
  }
}
