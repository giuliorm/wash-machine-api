package ru.juriasan.washmachineapi.controllers.exception;

/**
 * This exception is thrown when the entity cannot be found in the database.
 */
public class DatabaseEntityNotFoundException extends RuntimeException {

  public DatabaseEntityNotFoundException(String message) {
    super(message);
  }
}
