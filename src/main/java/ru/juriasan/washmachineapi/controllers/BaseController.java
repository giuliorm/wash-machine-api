package ru.juriasan.washmachineapi.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.DatabaseEntityNotFoundException;
import ru.juriasan.washmachineapi.controllers.exception.InvalidParameterException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

@RequestMapping("/")
@RestController()
public class BaseController {

  @Autowired
  protected WashMachineRepository repository;

  protected static final String MACHINE_MODEL_NAME_IS_NULL = "Machine name parameter is null.";
  protected static final String MACHINE_MODEL_NAME_IS_NOT_FOUND =
      "Machine with name %s cannot be found in the database";

  private static final String HELLO = "Welcome to the Wash Machine API";

  protected WashMachine findByModelName(String modelName, String errorMessageFormat) {
    if ( modelName == null ) {
      throw new InvalidParameterException(String.format(errorMessageFormat, MACHINE_MODEL_NAME_IS_NULL));
    }
    WashMachine machine = repository.findByModelName(modelName);
    if ( machine == null ) {
      throw new DatabaseEntityNotFoundException(String.format(errorMessageFormat,
          String.format(MACHINE_MODEL_NAME_IS_NOT_FOUND, modelName)));
    }
    return machine;
  }

  @RequestMapping("/")
  public String hello() {
    return HELLO;
  }

  @RequestMapping("/all")
  public List<String> findAll() {
    return repository.findAll().stream().map(WashMachine::getModelName).collect(Collectors.toList());
  }
}
