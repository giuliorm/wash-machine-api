package ru.juriasan.washmachineapi.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WashMachine {

  @Id
  private ObjectId id;

  @Indexed(unique = true)
  private String modelName;
  private boolean turnedOn;
  private WashState state;
  private WashMode currentWashMode;

  public WashMachine() {}

  public WashMachine(String modelName) {
      this.modelName = modelName;
  }

  public WashMachine(String modelName, boolean turnedOn, WashState state, WashMode mode) {
    this.modelName = modelName;
    this.turnedOn = turnedOn;
    this.state = state;
    this.currentWashMode = mode;
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

  private <T> void appendProperty(StringBuilder stringBuilder,  String propertyName, T property) {
     stringBuilder.append(propertyName);
     stringBuilder.append(":");
     stringBuilder.append(String.format("'%s'", property == null ? "" : property.toString()));
     stringBuilder.append(",");
  }
}

