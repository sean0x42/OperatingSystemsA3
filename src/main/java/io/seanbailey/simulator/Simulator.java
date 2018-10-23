package io.seanbailey.simulator;

import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.Process;

/**
 * Manages and runs the simulation according to the assignment specification.
 * @author Sean Bailey c3279343
 */
public class Simulator {

  private static final Logger logger = new Logger();

  private final int frames;
  private final int timeQuantum;
  private final Process[] processes;

  /**
   * Constructs a new simulator.
   * @param frames Number of memory frames available to system.
   * @param timeQuantum Time quantum available to processes in relevant 
   *                    scheduling algorithms.
   * @param processes An array of processes to run through the simulation.
   */
  public Simulator(int frames, int timeQuantum, Process[] processes) {
    this.frames = frames;
    this.timeQuantum = timeQuantum;
    this.processes = processes;
    logger.debug("Creating simulation with the following values:");
    logger.debug("frames: %d", frames);
    logger.debug("timeQuantum: %d", timeQuantum);
    logger.debug("processes: %d", processes.length);
  }
}
