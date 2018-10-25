package io.seanbailey.simulator.util;

import io.seanbailey.simulator.process.Process;

/**
 * A utility class for working with 
 * {@link{io.seanbailey.simulator.process.Process processes}.
 * @author Sean Bailey c3279343
 */
public class ProcessUtils {

  /**
   * Performs a deep clone of a process array.
   *
   * <p>
   * This is particular useful when running the simulation multiple times with
   * the same process array, to prevent one simulation from interfering with
   * another.
   * </p>
   *
   * @param original An array of processes to clone.
   */
  public static Process[] clone(Process[] original) {
    Process[] clone = new Process[original.length];

    // Copy each process from the original array
    for (int i = 0; i < original.length; i++) {
      clone[i] = new Process(original[i]);
    }

    return clone;
  }
}
