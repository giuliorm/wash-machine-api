package ru.juriasan.washmachineapi.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

@RestController
public class MachineController extends BaseController {

  public static final String HELLO = "Welcome to the Wash Machine API";
  public static final String CANNOT_GET_MACHINE_INFO_FORMAT = "Cannot get machine info: %s";
  public static final String CANNOT_REMOVE_MACHINE_FORMAT = "Cannot remove machine: %s";
  public static final String MACHINE_REMOVED_SUCCESSFULLY = "Machine has been removed successfully.";

  @RequestMapping("/")
  public String hello() {
    return HELLO;
  }

  @RequestMapping("/machine/all")
  public List<WashMachine> findAll() {
    return service.findAll();
  }

  @RequestMapping("/machine/{modelName}/create")
  public WashMachine createMachine(@PathVariable String modelName) {
    WashMachine machine = new WashMachine(modelName, false, WashState.NONE, WashMode.NONE);
    return service.save(machine);
  }

  @RequestMapping("/machine/{modelName}/get")
  public WashMachine getMachine(@PathVariable String modelName) {
    return findByModelName(modelName, CANNOT_GET_MACHINE_INFO_FORMAT);
  }

  @RequestMapping("/machine/{modelName}/remove")
  public String removeMachine(@PathVariable String modelName) {
    WashMachine machine = findByModelName(modelName, CANNOT_REMOVE_MACHINE_FORMAT);
    service.remove(machine);
    return MACHINE_REMOVED_SUCCESSFULLY;
  }
}
