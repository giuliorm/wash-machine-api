package ru.juriasan.washmachineapi.controllers.exception;

public class DatabaseEntityNotFoundException extends RuntimeException {

  public DatabaseEntityNotFoundException(String message) {
    super(message);
  }
}
