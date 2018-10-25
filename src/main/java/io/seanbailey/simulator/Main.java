package io.seanbailey.simulator;

import io.seanbailey.simulator.process.Process;
import io.seanbailey.simulator.Simulator;
import io.seanbailey.simulator.ValidationException;
import io.seanbailey.simulator.parser.ParseException;
import io.seanbailey.simulator.parser.Parser;
import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.util.ProcessUtils;
import java.io.File;

/**
 * Main entry point to the application. For an entry point that conforms to the
 * assignment specifications see @link{c3279343A3}.
 * @see c3279343A3
 * @author Sean Bailey c3279343
 */
public class Main {

  private static final Logger logger = new Logger();

  /**
   * Main entry point.
   * @param args An array of command line arguments from STDIN.
   * @see c3279343A3
   */
  public static void main(String[] args) {
    // Check arg length
    if (args.length < 3) {
      logger.error("Not enough args.");
      logger.info("java c3279343A3 <frames> <quantum_size> <process...>");
      return;
    }

    // Init
    int frames;
    int timeQuantum;
    Process[] processes = new Process[args.length - 2];

    // Validate input
    try {
      frames = validateInt(args[0]);
      timeQuantum = validateInt(args[1]);

      for (int i = 2; i < args.length; i++) {
        File file = validateFile(args[i]);
        processes[i - 2] = Parser.parse(file, args[i]);
      }
    } catch (ValidationException | ParseException exception) {
      logger.error(exception.getMessage());
      return;
    }

    // Print if debug is enabled in this build
    if (Logger.ENABLE_DEBUG) {
      logger.debug("Creating simulation with the following values:");
      logger.debug("  Frames: %d", frames);
      logger.debug("  Time Quantum: %d", timeQuantum);
      logger.debug("  Processes:");
      for (Process process : processes) {
        logger.debug("  » %s", process);
      }
    }

    // Start simulation
    Simulator sim = new Simulator(frames, timeQuantum);
    sim.simulate(ProcessUtils.clone(processes));
//    sim.simulate(ProcessUtils.clone(processes));
  }

  /**
   * Validates an integer. Ensures that it is both a valid int, and a positive,
   * non-zero value.
   * @param value Value to validate.
   * @return A valid integer.
   * @throws ValidationException if the value is not valid.
   */
  private static int validateInt(String value) throws ValidationException {
    // Init
    int i;

    // Attempt to parse int
    try {
      i = Integer.parseInt(value);
    } catch (NumberFormatException ignored) {
      throw new ValidationException("'%s' is not a valid number.", value);
    }

    // Ensure int is non-zero, positive number
    if (i <= 0) {
      throw new ValidationException("%d must be greater than 0.", i);
    }

    return i;
  }

  /**
   * Validates the given file path. Note that this does not perform any
   * validation on the contents of the file, it only ensures that the file
   * exists and is readable.
   * @param path Path to file.
   * @return A valid file.
   * @throws ValidationException if the path is not valid.
   */
  private static File validateFile(String path) throws ValidationException {
    // init
    File file = new File(path);

    // Ensure file exists
    if (!file.exists()) {
      throw new ValidationException("File '%s' not found.", path);
    }

    // Ensure file is not a directory
    if (file.isDirectory()) {
      throw new ValidationException("'%s' is a directory. Must be a file.", path);
    }

    // Ensure file is readable
    if (!file.canRead()) {
      throw new ValidationException("Cannot read file '%s'.", path);
    }

    return file;
  }
}
