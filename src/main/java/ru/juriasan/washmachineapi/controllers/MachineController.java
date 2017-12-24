package ru.juriasan.washmachineapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.juriasan.washmachineapi.domain.WashMachine;

@RestController
public class MachineController extends BaseController {

  private static final String HELLO = "Welcome to the Wash Machine API";

  @RequestMapping("/")
  public String hello() {
    return HELLO;
  }

  @RequestMapping("/machine/all")
  public List<String> findAll() {
    return repository.findAll().stream().map(WashMachine::getModelName).collect(Collectors.toList());
  }

  @RequestMapping("/machine/{modelName}/create")
  public String createMachine(@PathVariable String modelName) {
    return null;
  }

  @RequestMapping("/machine/{modelName}/get")
  public String getMachine(@PathVariable String modelName) {
    return null;
  }

}
