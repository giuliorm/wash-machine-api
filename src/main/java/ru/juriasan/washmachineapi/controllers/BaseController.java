package ru.juriasan.washmachineapi.controllers;

public class BaseController {

  protected static final String MACHINE_ID_IS_NULL = "Machine id parameter is null.";
  protected static final String MACHINE_IS_NOT_FOUND_FORMAT = "Machine with id %s cannot be found in the database.";
  protected static final String MACHINE_MODEL_NAME_IS_NULL = "Machine name parameter is null.";
  protected static final String MACHINE_MODEL_NAME_IS_NOT_FOUND =
      "Machine with name %s cannot be found in the database";
}
