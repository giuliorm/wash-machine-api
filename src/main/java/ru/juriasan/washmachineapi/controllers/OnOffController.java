package ru.juriasan.washmachineapi.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.TurnOffException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashState;

/**
 * This is a controller, which is responsible for turning machines off and on.
 * Contains the following API methods:
 *
 * /turn/{modelName}/on
 * /turn/{modelName}/off
 * /turn/{modelName}/isTurnedOn
 */
@RequestMapping("/turn")
@RestController
public class OnOffController extends BaseController {

  public static final String CANNOT_TURN_MACHINE_OFF_FORMAT = "Cannot turn machine off: %s";
  public static final String MACHINE_IN_STATE_FORMAT = "Machine is currently in %s state, cannot be turned off.";

  /**
   * This method sets the turnedOn flag of the machine with specified modelName to true.
   * The updated machine state is then saved to the database.
   *
   * @param modelName a WashMachine model name
   * @return updated WashMachine entity
   */
  @RequestMapping("/{modelName}/on")
  public WashMachine turnOn(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName);
    machine.turnOn();
    return service.save(machine);
  }

  /**
   * This method sets the turnedOn flag of the machine with specified modelName to false.
   * The updated machine state is then saved to the database.
   * The machine cannot be turned off, if it's not in the READY or NONE state.
   *
   * @param modelName a WashMachine model name
   * @throws TurnOffException if the machine cannot be turned off.
   * @return updated WashMachine entity
   */
  @RequestMapping("/{modelName}/off")
  public WashMachine turnOff(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName);
    WashState state = machine.getState();
    if ( state != null && state != WashState.NONE && state != WashState.READY ) {
      throw new TurnOffException(String.format(CANNOT_TURN_MACHINE_OFF_FORMAT,
          String.format(MACHINE_IN_STATE_FORMAT, state)));
    }
    machine.turnOff();
    return service.save(machine);
  }

  /**
   * Gets the turnedOn flag of the machine with specified modelName.
   *
   * @param modelName WashMachine model name
   * @return isTurnedOn flag
   */
  @RequestMapping("/{modelName}/isTurnedOn")
  public boolean isTurnedOn(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName);
    return machine.isTurnedOn();
  }
}
