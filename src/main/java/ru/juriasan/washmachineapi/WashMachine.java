package ru.juriasan.washmachineapi;

import org.springframework.data.annotation.Id;

public class WashMachine {

  @Id
  public String id;

  public String modelName;
  public boolean turnedOn;
  public String state;
  public String currentWashMode;

  public WashMachine() {}

  public WashMachine(String modelName) {
      this.modelName = modelName;
  }

  @Override
  public String toString() {
      return String.format(
              "WashMachine[id=%s, modelName='%s']",
              id, modelName);
  }
}

