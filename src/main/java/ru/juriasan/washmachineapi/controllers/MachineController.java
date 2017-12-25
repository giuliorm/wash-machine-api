package ru.juriasan.washmachineapi.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

/**
 * This is a CRUD controller for the WashMachine.
 * It contains the following API methods:
 *
 * /
 * /machine/all
 * /machine/{modelName}/create
 * /machine/{modelName}/get
 * /machine/{modelName}/remove
 */
@RestController
public class MachineController extends BaseController {

  public static final String HELLO = "Welcome to the Wash Machine API";
  public static final String MACHINE_REMOVED_SUCCESSFULLY = "Machine has been removed successfully.";

  @RequestMapping("/")
  public String hello() {
    return HELLO;
  }

  /**
   * This method is used to obtains all WashMachine objects in the database.
   *
   * @return the list of all WashMachine objects, which has been found in the database.
   * Returns the empty list, if the database is empty.
   */
  @RequestMapping("/machine/all")
  public List<WashMachine> findAll() {
    return service.findAll();
  }

  /**
   * This method creates new WashMachine with the following parameters and saves it to the database:
   *
   * - turnedOn: false
   * - washState: WashState.NONE
   * - washMode: WashMode.NONE
   *
   * The exception is thrown, if the WashMachine with the same model name already exists in the database.
   *
   * @param modelName The WashMachine model name
   * @return WashMachine entity, which has been saved to the database.
   */
  @RequestMapping("/machine/{modelName}/create")
  public WashMachine createMachine(@PathVariable String modelName) {
    WashMachine machine = new WashMachine(modelName, false, WashState.NONE, WashMode.NONE);
    return service.save(machine);
  }

  /**
   * This methods obtains the info for the WashMachine with the specified modelName.
   *
   * @param modelName a WashMachine model name
   * @return WashMachine entity
   */
  @RequestMapping("/machine/{modelName}/get")
  public WashMachine getMachine(@PathVariable String modelName) {
    return findByModelName(modelName);
  }

  /**
   * Removes the machine with specified model name.
   * An exception is thrown if the machine with modelName doesn't exist
   * in the database.
   *
   * @param modelName a WashMachine model name
   * @return The string message, indicating that machine has been removed successfully.
   */
  @RequestMapping("/machine/{modelName}/remove")
  public String removeMachine(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName);
    service.remove(machine);
    return MACHINE_REMOVED_SUCCESSFULLY;
  }
}
