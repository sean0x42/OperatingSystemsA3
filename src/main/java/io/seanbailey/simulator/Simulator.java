package io.seanbailey.simulator;

import io.seanbailey.simulator.event.Event;
import io.seanbailey.simulator.event.RequestPageEvent;
import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.process.Process;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Manages and runs the simulation according to the assignment specification.
 * @author Sean Bailey c3279343
 */
public class Simulator {

  private static final Logger logger = new Logger();

  private final int frames;
  private final int timeQuantum;

  private Process[] processes;
  private PriorityQueue<Event> eventQueue;
  private Queue<Process> readyQueue;
  private int currentTime;
  private Memory memory;

  /**
   * Constructs a new simulator.
   * @param frames Number of memory frames available to system.
   * @param timeQuantum Time quantum available to processes in relevant 
   *                    scheduling algorithms.
   */
  public Simulator(int frames, int timeQuantum) {
    this.frames = frames;
    this.timeQuantum = timeQuantum;
  }

  /**
   * Starts the simulation.
   * @param processes A list of processes to include in the simulation.
   */
  public void simulate(Process[] processes) {
    // Step 1: Initialise
    init(processes);

    // Continue until the event queue is empty
    while (!eventQueue.isEmpty()) {
      Event event = eventQueue.poll();
      currentTime = event.getTime();
      logger.debug("Executing event: %s", event);
      event.run();
    }
  }

  /**
   * Registers a new event.
   * @param event Event to register.
   */
  public void registerEvent(Event event) {
    eventQueue.add(event);
    logger.debug("Registered event: %s", event);
  }

  /**
   * Initialises memory, ready and event queues, and more.
   * @param A list of processes to include in the simulation.
   */
  private void init(Process[] processes) {
    logger.debug("Initialising simulation...");

    this.processes = processes;
    eventQueue = new PriorityQueue<>();
    readyQueue = new LinkedList<>();
    currentTime = 0;
    memory = new Memory(frames);
    
    // Assign process with frames in memory
    int range = frames / processes.length;
    for (int i = 0; i < processes.length; i++) {
      memory.assignFrames(range, processes[i]);
    }

    // Add processes to ready queue
    for (Process process : processes) {
      readyQueue.offer(process);
    }
  }

  /**
   * Creates a few events to kick off the simulation.
   */
  private void createInitialEvents() {
    logger.debug("Creating initial events...");

    for (Process process : processes) {
      registerEvent(new RequestPageEvent(0, this, process.getNextPage(), process));
    }
  }

  public Memory getMemory() {
    return memory;
  }
}
