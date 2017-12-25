package ru.juriasan.washmachineapi.controllers.exception;

/**
 * This exception is thrown when the WashMachine cannot be turned off (the 'isTurnedOn' property cannot be set to
 * 'false') because the state or the other conditions of the machine doesn't allow that.
 */
public class TurnOffException extends RuntimeException {

  public TurnOffException(String message) {
    super(message);
  }
}
