package com.aegis.inventory.exception;

public class UserAlreadyExist extends RuntimeException {
  public UserAlreadyExist(String message) {
    super(message);
  }
}
