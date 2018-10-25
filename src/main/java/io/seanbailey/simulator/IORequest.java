package io.seanbailey.simulator;

import io.seanbailey.simulator.process.Process;

/**
 * Represents a single IO request, managed by the
 * @link{io.seanbailey.simulator.IOController}.
 * @see io.seanbailey.simulator.IOController
 * @author Sean Bailey c3279343
 */
public class IORequest {

  private final Process process;
  private final int page;
  private final int completionTime;

  /**
   * Constructs a new IO request.
   * @param process Owning process.
   * @param page Requested page.
   * @param completionTime Time that the request will complete.
   */
  public IORequest(Process process, int page, int completionTime) {
    this.process = process;
    this.page = page;
    this.completionTime = completionTime;
  }

  /**
   * @return A representation of this request as a string.
   */
  @Override
  public String toString() {
    return "IORequest{" +
      "process: " + process.getId() +
      ", page: " + page +
      ", completionTime: " + completionTime +
      "}";
  }

  public Process getProcess() {
    return process;
  }

  public int getPage() {
    return page;
  }

  public int getCompletionTime() {
    return completionTime;
  }
}
