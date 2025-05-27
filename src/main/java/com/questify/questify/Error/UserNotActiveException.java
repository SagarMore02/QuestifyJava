package com.questify.questify.Error;

public class UserNotActiveException extends RuntimeException {

  public UserNotActiveException(String message) {
    super(message);
  }
}
