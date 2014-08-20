package ch.kerbtier.procfx;

public class WrongParameterException extends RuntimeException {
  public WrongParameterException(String message) {
    super(message);
  }
}
