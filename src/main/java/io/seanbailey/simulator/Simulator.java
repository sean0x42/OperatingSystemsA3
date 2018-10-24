package io.seanbailey.simulator;

import io.seanbailey.simulator.event.Event;
import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.process.Process;
import java.util.PriorityQueue;

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

  private PriorityQueue<Event> eventQueue = new PriorityQueue<>();
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
   *
   * <p>
   * The simulation takes place over the following stages:
   * <ol>
   *   <li>Allocate each process with a portion of frames from main memory.</li>
   *   <li>Kick of the simulation by creating initial events.</li>
   * </ol>
   * </p>
   */
  public void simulateAndPrint() {
    // Create initial events to kick off the simulation
    

    currentTime = 0;

    // Continue until the event queue is empty
    while (!eventQueue.isEmpty()) {
      Event event = eventQueue.poll();
      currentTime = event.getTime();
      event.run();
    }
  }

  /**
   * Registers a new event.
   * @param event Event to register.
   */
  public void registerEvent(Event event) {
    eventQueue.add(event);
  }
}
