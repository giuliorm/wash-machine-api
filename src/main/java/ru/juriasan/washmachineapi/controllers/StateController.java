package ru.juriasan.washmachineapi.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

@RequestMapping("/state")
@RestController
public class StateController extends BaseController {

  public static final String CANNOT_SET_STATE_FORMAT = "Cannot set state: %s";
  public static final String CANNOT_SET_CURRENT_WASH_MODE_FORMAT = "Cannot set current wash mode: %s";
  public static final String STATE_IS_NULL = "State parameter is null.";
  public static final String WASH_MODE_IS_NULL = "Wash mode is null.";

  @RequestMapping("/{modelName}/getState")
  public WashState getState(@PathVariable String modelName) {
    WashState state = findByModelName(modelName).getState();
    return state != null ? state : WashState.NONE;
  }

  @RequestMapping("/{modelName}/setState")
  public WashMachine setState(@PathVariable String modelName, @RequestParam(name = "state") WashState state) {
    if ( state == null ) {
      throw new NullPointerException(String.format(CANNOT_SET_STATE_FORMAT, STATE_IS_NULL));
    }
    WashMachine machine = findByModelName(modelName);
    machine.setState(state);
    return service.save(machine);
  }

  @RequestMapping("/{modelName}/getMode")
  public WashMode getCurrentWashMode(@PathVariable String modelName) {
    WashMode mode = findByModelName(modelName).getCurrentWashMode();
    return mode != null ? mode : WashMode.NONE;
  }

  @RequestMapping("/{modelName}/setMode")
  public WashMachine setCurrentWashMode(@PathVariable String modelName, @RequestParam(name = "mode") WashMode washMode) {
    if ( washMode == null ) {
      throw new NullPointerException(String.format(CANNOT_SET_CURRENT_WASH_MODE_FORMAT, WASH_MODE_IS_NULL));
    }
    WashMachine machine = findByModelName(modelName);
    machine.setCurrentWashMode(washMode);
    return service.save(machine);
  }
}
