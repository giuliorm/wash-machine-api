package ru.juriasan.washmachineapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.TurnOffException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashState;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

@RestController
@RequestMapping("/turn")
public class OnOffController extends BaseController {

  private static final String CANNOT_TURN_MACHINE_ON_FORMAT = "Cannot turn machine on: %s";
  private static final String CANNOT_TURN_MACHINE_OFF_FORMAT = "Cannot turn machine off: %s";
  private static final String TURNED_ON_SUCCESSFULLY = "Machine has been turned on successfully";
  private static final String TURNED_OFF_SUCCESSFULLY = "Machine has been turned off successfully";
  private static final String MACHINE_IN_STATE_FORMAT = "Machine is currently in %s state, cannot be turned off.";

  @RequestMapping("/{modelName}/on")
  public String turnOn(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_TURN_MACHINE_ON_FORMAT);
    machine.turnOn();
    repository.save(machine);
    return TURNED_ON_SUCCESSFULLY;
  }

  // transactional integrity?...
  @RequestMapping("/{modelName}/off")
  public String turnOff(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_TURN_MACHINE_OFF_FORMAT);
    WashState state = machine.getState();
    if ( state != null && state != WashState.NONE && state != WashState.READY ) {
      throw new TurnOffException(String.format(CANNOT_TURN_MACHINE_OFF_FORMAT,
          String.format(MACHINE_IN_STATE_FORMAT, state)));
    }
    machine.turnOff();
    repository.save(machine);
    return TURNED_OFF_SUCCESSFULLY;
  }

  @RequestMapping("/{modelName}/isTurnedOn")
  public boolean isTurnedOn(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_TURN_MACHINE_ON_FORMAT);
    return machine.isTurnedOn();
  }
}
