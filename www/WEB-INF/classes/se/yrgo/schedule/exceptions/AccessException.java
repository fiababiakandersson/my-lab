package se.yrgo.schedule.exceptions;

public class AccessException extends Exception {
  public AccessException(String msg) {
    super(msg);
  }

  public AccessException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
