package ru.juriasan.washmachineapi.controllers.exception;

public class TurnOffException extends RuntimeException {

  public TurnOffException(String message) {
    super(message);
  }
}
