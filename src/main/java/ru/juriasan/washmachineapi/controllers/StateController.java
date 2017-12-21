package ru.juriasan.washmachineapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.DatabaseEntityNotFoundException;
import ru.juriasan.washmachineapi.controllers.exception.InvalidParameterException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

@RestController
@RequestMapping("/state")
public class StateController extends BaseController {

  private static final String CANNOT_SET_STATE_FORMAT = "Cannot set state: %s";
  private static final String CANNOT_GET_STATE_FORMAT = "Cannot get state: %s";
  private static final String CANNOT_GET_CURRENT_WASH_MODE_FORMAT = "Cannot get current wash mode: %s";
  private static final String CANNOT_SET_CURRENT_WASH_MODE_FORMAT = "Cannot set current wash mode: %s";
  private static final String STATE_IS_NULL = "State parameter is null.";
  private static final String STATE_IS_UPDATED_SUCCESSFULLY = "State has been updated successfully.";
  private static final String WASH_MODE_IS_UPDATED_SUCCESSFULLY = "Wash mode has been updated successfully.";
  private static final String WASH_MODE_IS_NULL = "Wash mode is null.";

  @Autowired
  private WashMachineRepository repository;

  private WashMachine findByModelName(String modelName, String errorMessageFormat) {
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

  @RequestMapping("/getState")
  public WashState getState(@RequestParam(name = "modelName") String modelName) {
    return findByModelName(modelName, CANNOT_GET_STATE_FORMAT).getState();
  }

  @RequestMapping("/setState")
  public String setState(@RequestParam(name = "modelName") String modelName,
                             @RequestParam(name = "state") WashState state) {
    if ( state == null ) {
      throw new InvalidParameterException(String.format(CANNOT_SET_STATE_FORMAT, STATE_IS_NULL));
    }
    WashMachine machine = findByModelName(modelName, CANNOT_SET_STATE_FORMAT);
    machine.setState(state);
    repository.save(machine);
    return STATE_IS_UPDATED_SUCCESSFULLY;
  }

  @RequestMapping("/getMode")
  public WashMode getCurrentWashMode(@RequestParam(name = "modelName") String modelName) {
    return findByModelName(modelName, CANNOT_GET_CURRENT_WASH_MODE_FORMAT).getCurrentWashMode();
  }

  @RequestMapping("/setMode")
  public String setCurrentWashMode(@RequestParam(name = "modelName") String modelName,
                                       @RequestParam(name = "mode") WashMode washMode) {
    if ( washMode == null ) {
      throw new InvalidParameterException(String.format(CANNOT_SET_CURRENT_WASH_MODE_FORMAT, STATE_IS_NULL));
    }
    WashMachine machine = findByModelName(modelName, CANNOT_SET_CURRENT_WASH_MODE_FORMAT);
    machine.setCurrentWashMode(washMode);
    repository.save(machine);
    return WASH_MODE_IS_UPDATED_SUCCESSFULLY;
  }
}
