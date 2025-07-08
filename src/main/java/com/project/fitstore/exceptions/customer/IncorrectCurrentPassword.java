package com.project.fitstore.exceptions.customer;

public class IncorrectCurrentPassword extends RuntimeException {
  public IncorrectCurrentPassword() {
    super("Current password is incorrect.");
  }

  public IncorrectCurrentPassword(String message) {
      super(message);
  }
}
