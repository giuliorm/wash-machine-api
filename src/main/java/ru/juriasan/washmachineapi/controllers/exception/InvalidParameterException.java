package ru.juriasan.washmachineapi.controllers.exception;

public class InvalidParameterException extends RuntimeException {

  public InvalidParameterException(String message) {

    super(message);
  }
}
