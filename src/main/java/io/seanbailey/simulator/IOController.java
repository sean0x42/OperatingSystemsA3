package io.seanbailey.simulator;

import io.seanbailey.simulator.process.Process;
import io.seanbailey.simulator.process.State;
import io.seanbailey.simulator.util.Logger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A controller class which is responsible for loading pages from secondary
 * memory, into main memory.
 * @author Sean Bailey c3279343
 */
public class IOController {

  private static final Logger logger = new Logger();
  private static final int IO_DURATION = 6;

  private final Simulator simulator;
  private final Memory memory;
  private Queue<IORequest> requests;

  /**
   * Constructs a new IO Controller.
   * @param simulator Parent simulator class.
   * @param memory Main memory to load into.
   */
  public IOController(Simulator simulator, Memory memory) {
    this.simulator = simulator;
    this.memory = memory;
    requests = new LinkedList<>();
  }

  /**
   * Loads the given page into memory.
   * @param process Owning process.
   * @param page Page to load.
   * @param time Current simulation time.
   */
  public void load(Process process, int page, int time) {
    IORequest request = new IORequest(process, page, time + IO_DURATION);
    requests.offer(request);
    logger.debug("Queued up a new I/O request: %s", request);
  }

  /**
   * Checks to see if any I/O requests have been completed.
   * @param time Current simulation time.
   */
  public boolean hasInterrupts(int time) {
    IORequest request = requests.peek();
    return request != null && request.getCompletionTime() <= time;
  }

  /**
   * Release any IO requests that have been completed.
   * @param time Current simulation time.
   */
  public void releaseInterrupts(int time) {
    while (!requests.isEmpty() && requests.peek().getCompletionTime() <= time) {
      IORequest request = requests.poll();
      Process process = request.getProcess();

      // Load into memory
      memory.loadPage(process, request.getPage(), time);

      // Release process
      process.setState(State.READY);
      simulator.addToReadyQueue(process);
    }
  }
}
