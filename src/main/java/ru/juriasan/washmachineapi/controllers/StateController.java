package ru.juriasan.washmachineapi.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.controllers.exception.InvalidParameterException;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

@RequestMapping("/state")
@RestController
public class StateController extends BaseController {

  private static final String CANNOT_SET_STATE_FORMAT = "Cannot set state: %s";
  private static final String CANNOT_GET_STATE_FORMAT = "Cannot get state: %s";
  private static final String CANNOT_GET_CURRENT_WASH_MODE_FORMAT = "Cannot get current wash mode: %s";
  private static final String CANNOT_SET_CURRENT_WASH_MODE_FORMAT = "Cannot set current wash mode: %s";
  private static final String STATE_IS_NULL = "State parameter is null.";
  private static final String STATE_IS_UPDATED_SUCCESSFULLY = "State has been updated successfully.";
  private static final String WASH_MODE_IS_UPDATED_SUCCESSFULLY = "Wash mode has been updated successfully.";
  private static final String WASH_MODE_IS_NULL = "Wash mode is null.";

  @RequestMapping("/{modelName}/getState")
  public WashState getState(@PathVariable String modelName) {
    WashState state = findByModelName(modelName, CANNOT_GET_STATE_FORMAT).getState();
    return state != null ? state : WashState.NONE;
  }

  @RequestMapping("/{modelName}/setState")
  public String setState(@PathVariable String modelName, @RequestParam(name = "state") WashState state) {
    if ( state == null ) {
      throw new InvalidParameterException(String.format(CANNOT_SET_STATE_FORMAT, STATE_IS_NULL));
    }
    WashMachine machine = findByModelName(modelName, CANNOT_SET_STATE_FORMAT);
    machine.setState(state);
    repository.save(machine);
    return STATE_IS_UPDATED_SUCCESSFULLY;
  }

  @RequestMapping("/{modelName}/getMode")
  public WashMode getCurrentWashMode(@PathVariable String modelName) {
    WashMode mode = findByModelName(modelName, CANNOT_GET_CURRENT_WASH_MODE_FORMAT).getCurrentWashMode();
    return mode != null ? mode : WashMode.NONE;
  }

  @RequestMapping("/{modelName}/setMode")
  public String setCurrentWashMode(@PathVariable String modelName, @RequestParam(name = "mode") WashMode washMode) {
    if ( washMode == null ) {
      throw new InvalidParameterException(String.format(CANNOT_SET_CURRENT_WASH_MODE_FORMAT, WASH_MODE_IS_NULL));
    }
    WashMachine machine = findByModelName(modelName, CANNOT_SET_CURRENT_WASH_MODE_FORMAT);
    machine.setCurrentWashMode(washMode);
    repository.save(machine);
    return WASH_MODE_IS_UPDATED_SUCCESSFULLY;
  }
}
