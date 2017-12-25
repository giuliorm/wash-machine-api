package ru.juriasan.washmachineapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.juriasan.washmachineapi.controllers.exception.DatabaseEntityNotFoundException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.service.WashMachineService;

/**
 * This is a base class for all REST controllers. All the Wash Machine controllers should be inherited from it.
 */
public class BaseController {

  @Autowired
  protected WashMachineService service;

  public static final String MACHINE_MODEL_NAME_IS_NULL = "Machine name parameter is null.";
  public static final String MACHINE_MODEL_NAME_IS_NOT_FOUND =
      "Machine with name %s cannot be found in the database";

  /**
   * This methods searches WashMachine object by the specified model name.
   *
   *
   * @param modelName A unique model name, which is applied to find specified WashMachine object.
   * @throws NullPointerException if the modelName is null
   * @throws DatabaseEntityNotFoundException if the machine with the specified modelName doesn't exist in the database.
   * @return WashMachine object
   */
  protected WashMachine findByModelName(String modelName) {
    if ( modelName == null ) {
      throw new NullPointerException(MACHINE_MODEL_NAME_IS_NULL);
    }
    WashMachine machine = service.findByModelName(modelName);
    if ( machine == null ) {
      throw new DatabaseEntityNotFoundException(String.format(MACHINE_MODEL_NAME_IS_NOT_FOUND, modelName));
    }
    return machine;
  }
}
