package io.seanbailey.simulator.event;

import io.seanbailey.simulator.Simulator;

/**
 * A runnable event occurring at a discrete point in time. These events are
 * executed at the appropriate time by the
 * @link{io.seanbailey.simulator.Simulator}.
 * @see io.seanbailey.simulator.Simulator
 * @author Sean Bailey c3279343
 */
public abstract class Event implements Comparable<Event> {

  private final int time;
  private final Simulator simulator;

  /**
   * Constructs a new event.
   * @param time Time that this event occurs.
   * @param simulator A reference to the simulator for scheduling additional
   *                  events.
   */
  public Event(int time, Simulator simulator) {
    this.time = time;
    this.simulator = simulator;
  }

  /**
   * Perform tasks associated with this event.
   */
  public abstract void run();

  /**
   * Returns the total ordering of this event compared to another one.
   * @param event Event to compare to.
   * @return the total order.
   */
  @Override
  public int compareTo(Event event) {
    return time - event.getTime();
  }

  /**
   * @return A representation of this event as a string.
   */
  @Override
  public abstract String toString();

  public int getTime() {
    return time;
  }

  protected Simulator getSimulator() {
    return simulator;
  }
}
