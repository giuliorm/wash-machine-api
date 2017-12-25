package ru.juriasan.washmachineapi.domain;

/**
 * This is a enum, which contains all possible wash modes.
 * The NONE washMode denotes that washMachine is turned off or
 * in the READY state and the mode hasn't been specified yet.
 */
public enum WashMode {
  SILK,
  WOOL,
  NORMAL,
  NONE
}
