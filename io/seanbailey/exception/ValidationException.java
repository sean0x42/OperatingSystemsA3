package io.seanbailey.exception;

/**
 * An exception that can be thrown whenever validation of a certain resource
 * fails.
 * @author Sean Bailey c3279343
 */
public class ValidationException extends Throwable {

  /**
   * Constructs a new validation exception.
   * @param message Error message.
   */
  public ValidationException(String message) {
    super(message);
  }

  /**
   * Constructs a new validation exception.
   * @param format String format.
   * @param values Objects to populate format string with.
   */
  public ValidationException(String format, Object... values) {
    super(String.format(format, values));
  }
}
