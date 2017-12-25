package ru.juriasan.washmachineapi.domain;

/**
 * This is a enum, which contains all possible wash machine states.
 * The NONE WashState denotes that washMachine is turned off.
 */
public enum WashState {
  READY,
  WASHING,
  SPINNING,
  NONE
}
