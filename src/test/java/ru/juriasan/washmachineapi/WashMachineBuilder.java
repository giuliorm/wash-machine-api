package ru.juriasan.washmachineapi;

import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

public class WashMachineBuilder {

  private String modelName;
  private boolean isTurnedOn;
  private WashState state;
  private WashMode mode;

  public WashMachineBuilder modelName(String modelName) {
    this.modelName = modelName;
    return this;
  }

  public WashMachineBuilder state(WashState state) {
    this.state = state;
    return this;
  }

  public WashMachineBuilder mode(WashMode mode) {
    this.mode = mode;
    return this;
  }

  public WashMachineBuilder isTurnedOn(boolean turnedOn) {
    this.isTurnedOn = turnedOn;
    return this;
  }

  public WashMachine build() {
    return new WashMachine(modelName, isTurnedOn, state, mode);
  }
}
