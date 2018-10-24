package io.seanbailey.simulator.event;

import io.seanbailey.simulator.Simulator;
import io.seanbailey.simulator.process.Process;

/**
 * An event which can be fired whenever a process requests a page from the
 * operating system.
 * @see io.seanbailey.simulator.event.Event
 * @author Sean Bailey c3279343
 */
public class RequestFrameEvent extends Event {

  private final int frame;
  private final Process sender;

  /**
   * Constructs a new frame request event.
   * @param time Time that this event should occur.
   * @param simulator Simulating class.
   * @param frame Requested frame.
   * @param sender Requesting process.
   */
  public RequestFrameEvent(int time, Simulator simulator, int frame,
      Process sender) {
    super(time, simulator);
    this.frame = frame;
    this.sender = sender;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {

  }

  /**
   * @return {@inheritDoc}
   */
  @Override
  public String toString() {
    return "RequestFrameEvent{" +
      "time: " + getTime() +
      ", frame: " + frame +
      ", sender: " + sender +
      "}";
  }

  public int getFrame() {
    return frame;
  }

  public Process getSender() {
    return sender;
  }
}
