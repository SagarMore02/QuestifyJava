package com.questify.questify.Error;

public class InvalidLoginCredentialsException extends RuntimeException {

  public InvalidLoginCredentialsException(String message) {
    super(message);
  }
}
