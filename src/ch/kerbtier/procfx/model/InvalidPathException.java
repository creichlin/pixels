package ch.kerbtier.procfx.model;

public class InvalidPathException extends RuntimeException {

  public InvalidPathException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidPathException(String message) {
    super(message);
  }
  
}
