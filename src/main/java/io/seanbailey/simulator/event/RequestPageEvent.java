package io.seanbailey.simulator.event;

import io.seanbailey.simulator.Memory;
import io.seanbailey.simulator.Simulator;
import io.seanbailey.simulator.process.Process;

/**
 * An event which can be fired whenever a process requests a page from the
 * operating system.
 * @see io.seanbailey.simulator.event.Event
 * @author Sean Bailey c3279343
 */
public class RequestPageEvent extends Event {

  private final int page;
  private final Process sender;

  /**
   * Constructs a new frame request event.
   * @param time Time that this event should occur.
   * @param simulator Simulating class.
   * @param page Requested page.
   * @param sender Requesting process.
   */
  public RequestPageEvent(int time, Simulator simulator, int page,
      Process sender) {
    super(time, simulator);
    this.page = page;
    this.sender = sender;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    /*Memory memory = getSimulator().getMemory();
    if (!memory.isPageLoaded(sender, page)) {

      return;
    }

    memory.readPage(sender, page);*/
  }

  /**
   * @return {@inheritDoc}
   */
  @Override
  public String toString() {
    return "RequestFrameEvent{" +
      "time: " + getTime() +
      ", page: " + page +
      ", sender: " + sender +
      "}";
  }

  public int getPage() {
    return page;
  }

  public Process getSender() {
    return sender;
  }
}
