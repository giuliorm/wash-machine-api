package ru.juriasan.washmachineapi.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.TurnOffException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashState;

@RequestMapping("/turn")
@RestController
public class OnOffController extends BaseController {

  public static final String CANNOT_TURN_MACHINE_ON_FORMAT = "Cannot turn machine on: %s";
  public static final String CANNOT_TURN_MACHINE_OFF_FORMAT = "Cannot turn machine off: %s";
  public static final String MACHINE_IN_STATE_FORMAT = "Machine is currently in %s state, cannot be turned off.";

  @RequestMapping("/{modelName}/on")
  public WashMachine turnOn(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_TURN_MACHINE_ON_FORMAT);
    machine.turnOn();
    return service.save(machine);
  }

  @RequestMapping("/{modelName}/off")
  public WashMachine turnOff(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_TURN_MACHINE_OFF_FORMAT);
    WashState state = machine.getState();
    if ( state != null && state != WashState.NONE && state != WashState.READY ) {
      throw new TurnOffException(String.format(CANNOT_TURN_MACHINE_OFF_FORMAT,
          String.format(MACHINE_IN_STATE_FORMAT, state)));
    }
    machine.turnOff();
    return service.save(machine);
  }

  @RequestMapping("/{modelName}/isTurnedOn")
  public boolean isTurnedOn(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_TURN_MACHINE_ON_FORMAT);
    return machine.isTurnedOn();
  }
}
