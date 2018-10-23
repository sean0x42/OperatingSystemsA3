package io.seanbailey.simulator;

import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.Process;

/**
 * Manages and runs the simulation according to the assignment specification.
 * @author Sean Bailey c3279343
 */
public class Simulator {

  private static final Logger logger = new Logger();
  private static final int MAX_PAGES_PER_PROCESS = 50;

  private final int frames;
  private final int timeQuantum;
  private final Process[] processes;

  private int currentPage = -1;

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
  }

  /**
   * Starts the simulation.
   *
   * <p>
   * Note that the simulation takes place over a number of steps.
   * <ol>
   *   <li>Select a process for execution.</li>
   * </ol>
   * </p>
   */
  public void start() {
        
  }
}
