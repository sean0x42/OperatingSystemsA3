package io.seanbailey.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import io.seanbailey.Process;
import io.seanbailey.exception.ParseException;
import io.seanbailey.exception.ValidationException;

/**
 * Parses a process file, returning a process or a parse exception.
 * @author Sean Bailey c3279343
 * @see io.seanbailey.Process
 * @see io.seanbailey.exception.ParseException
 */
public class Parser {

  /**
   * Parses a process file.
   * @param file Process file to parse.
   * @return A process.
   * @throws ParseException if the file cannot be parsed for any reason.
   * @throws ValidationException of the file is not valid.
   */
  public static Process parse(File file) throws ParseException, 
         ValidationException {

    // Init
    Scanner scanner;
    Process process = new Process();

    // Attempt to construct scanner
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException exception) {
      // This should theoretically never occur, since the main class checks if
      // files exist. But just in case.
      throw new ParseException("File '%s' not found.", file.getAbsolutePath());
    }

    // Iterate over lines of file
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      // Handle `end` keyword
      if (line.equalsIgnoreCase("end")) {
        break;
      }

      // Handle `begin` keyword
      if (line.equalsIgnoreCase("begin")) {
        continue;
      }

      process.addPage(parsePage(line));
    }

    return process; 
  }

  /**
   * Parses a given line of the file, to ensure it is a valid page.
   * @param value Value to parse.
   * @return Page as an integer.
   * @throws ValidationError if the line is invalid for any reason.
   */
  private static int parsePage(String value) throws ValidationException {
    int page;

    // Ensure valid is an integer.
    try {
      page = Integer.parseInt(value);
    } catch (NumberFormatException ignored) {
      throw new ValidationException(
          "Failed to parse process file. '%s' is not a number.", 
          value
      );
    }

    // Ensure value is positive, and non-zero
    if (page <= 0) {
      throw new ValidationException(
          "Failed to parse process file. %d must be greater than 0.", 
          page
      );
    }

    return page;
  }
}
