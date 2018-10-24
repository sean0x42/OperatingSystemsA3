package io.seanbailey.simulator;

import io.seanbailey.simulator.event.Event;
import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.process.Process;
import java.util.PriorityQueue;
import java.util.Queue;

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

  private Queue<Event> readyQueue = new PriorityQueue<>();
  private int currentTime;

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
   */
  public void simulateAndPrint() {
    currentTime = 0;
  }
}
