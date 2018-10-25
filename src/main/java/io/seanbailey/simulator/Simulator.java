package io.seanbailey.simulator;

import io.seanbailey.simulator.policy.Policy;
import io.seanbailey.simulator.process.Process;
import io.seanbailey.simulator.process.State;
import io.seanbailey.simulator.util.Logger;
import io.seanbailey.simulator.util.OutputTable;
import java.util.LinkedList;
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
  private Queue<Process> readyQueue;
  private Process running;
  private int currentTime;
  private Memory memory;
  private IOController ioController;
  private Policy policy;

  /**
   * Constructs a new simulator.
   * @param frames Number of memory frames available to system.
   * @param timeQuantum Maximum time quantum available to a process before it is
   *                    blocked.
   */
  public Simulator(int frames, int timeQuantum) {
    this.frames = frames;
    this.timeQuantum = timeQuantum;
  }

  /**
   * Starts the simulation.
   * @param processes A list of processes to include in the simulation.
   * @param policy Memory management policy.
   */
  public void simulate(Process[] processes, Policy policy) {
    // Step 1: Initialise
    init(processes, policy);

    // Step 2: Step through ready queue
    while (!isComplete()) {
      // Step 2a: IO interrupts
      handleInterrupts();

      // Step 2b: No ready processes
      if (readyQueue.isEmpty()) {
        currentTime++;
        continue;
      }

      // Step 2c: Handle ready process
      running = readyQueue.poll();
      handleRunningProcess();
    }

    // Step 3: Print results
    print();
  }

  /**
   * Initialises memory, ready and event queues, and more.
   * @param processes A list of processes to include in the simulation.
   * @param policy Memory management policy.
   */
  private void init(Process[] processes, Policy policy) {
    logger.debug("Initialising simulation...");

    this.policy = policy;
    this.processes = processes;
    readyQueue = new LinkedList<>();
    running = null;
    currentTime = 0;
    memory = new Memory(policy, frames);
    ioController = new IOController(this, memory);
    
    // Assign process with frames in memory, and add to ready queue
    int range = frames / processes.length;
    for (Process process : processes) {
      memory.assignFrames(range, process);
      readyQueue.offer(process);
      process.setState(State.READY);
    }
  }

  /**
   * Prints the simulationr results.
   */
  private void print() {
    logger.info("%s - Fixed:", policy.getShortName());
    OutputTable table = new OutputTable(3)
      .addRow("PID", "Process Name", "Turnaround Time", "#Faults", "Fault Times");

    // Add processes
    for (Process process : processes) {
      table.addRow(
          process.getId(), 
          process.getName(), 
          process.getTurnaroundTime(),
          process.getFaults(),
          process.getFaultTimesString()
      );
    }

    logger.info(table.toString());
  }

  /**
   * Determines whether the simulation is complete.
   * @return Whether the sim is complete.
   */
  private boolean isComplete() {
    for (Process process : processes) {
      if (process.getState() != State.EXIT) {
        return false;
      }
    }

    return true;
  }

  /**
   * Handles a running process by executing each of its instructions.
   */
  private void handleRunningProcess() {
    // Init
    int timeStarted = currentTime;
    running.setState(State.RUNNING);
    logger.debug("Running process %s", running);

    // Execute until blocked, or our time quantum expires.
    do {
      int page = running.getNextPage();
      handleInterrupts();

      // Check if page is current loaded. If not, block and wait for IO
      if (!memory.isPageLoaded(running, page)) {
        ioController.load(running, page, currentTime);
        running.setState(State.BLOCKED);
        running.addFault(currentTime);
        break;
      }

      // Execute and advance time
      memory.read(running, page, currentTime);
      running.removePage();
      currentTime++;

      // Handle proccesses that are finished running
      if (!running.hasNextPage()) {
        running.setState(State.EXIT);
        running.setTurnaroundTime(currentTime);
        break;
      }

      // Handle expired time quantum
      if (currentTime - timeStarted >= timeQuantum) {
        running.setState(State.READY);
        readyQueue.offer(running);
        break;
      }
    } while (true); // We'll break out manually when needed
  }

  /**
   * Checks to see if there are any I/O interrupts waiting to be handled. If so,
   * handles them.
   */
  private void handleInterrupts() {
    // Check if there are any interrupts to handle
    if (ioController.hasInterrupts(currentTime)) {
      ioController.releaseInterrupts(currentTime);
    }
  }

  /**
   * Adds the given process to the ready queue.
   *
   * <p>
   * This is purely so that the IO controller can return the blocked process
   * back to the ready queue once the IO operation has been completed.
   * </p>
   *
   * @param process Process to add.
   */
  public void addToReadyQueue(Process process) {
    readyQueue.offer(process);
  }
}
