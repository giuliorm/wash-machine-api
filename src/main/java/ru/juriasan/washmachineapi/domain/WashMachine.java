package ru.juriasan.washmachineapi.domain;

import org.springframework.data.annotation.Id;

public class WashMachine {

  @Id
  private String id;

  private String modelName;
  private boolean turnedOn;
  private WashState state;
  private WashMode currentWashMode;

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

  public String getModelName() {
    return this.modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public boolean isTurnedOn() {
    return this.turnedOn;
  }

  public void turnOn() {
    this.turnedOn = true;
  }

  public void turnOff() {
    this.turnedOn = false;
  }

  public void setState(WashState state) {
    this.state = state;
  }

  public WashState getState() {
    return this.state;
  }

  public void setCurrentWashMode(WashMode mode) {
    this.currentWashMode = mode;
  }

  public WashMode getCurrentWashMode() {
    return this.currentWashMode;
  }
}

