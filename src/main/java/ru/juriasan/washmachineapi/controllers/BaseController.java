package ru.juriasan.washmachineapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.DatabaseEntityNotFoundException;
import ru.juriasan.washmachineapi.controllers.exception.InvalidParameterException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.service.WashMachineService;

@RequestMapping("/")
@RestController()
public class BaseController {

  @Autowired
  protected WashMachineService service;

  public static final String MACHINE_MODEL_NAME_IS_NULL = "Machine name parameter is null.";
  public static final String MACHINE_MODEL_NAME_IS_NOT_FOUND =
      "Machine with name %s cannot be found in the database";

  protected WashMachine findByModelName(String modelName, String errorMessageFormat) {
    if ( modelName == null ) {
      throw new InvalidParameterException(String.format(errorMessageFormat, MACHINE_MODEL_NAME_IS_NULL));
    }
    WashMachine machine = service.findByModelName(modelName);
    if ( machine == null ) {
      throw new DatabaseEntityNotFoundException(String.format(errorMessageFormat,
          String.format(MACHINE_MODEL_NAME_IS_NOT_FOUND, modelName)));
    }
    return machine;
  }
}
