package io.seanbailey.simulator.process;

import io.seanbailey.simulator.process.State;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single process, to be handled by scheduling algorithms.
 * @author Sean Bailey c3279343
 */
public class Process {

  // Use to keep track of the current process id,
  private static int idSequence = 0;

  // Final variables
  private List<Integer> pages = new ArrayList<>();
  private final int id;
  private final String name;

  private State state = State.READY;

  /**
   * Constructs a new process.
   * @param name Human readable process name.
   */
  public Process(String name) {
    this.name = name;
    idSequence++;
    this.id = idSequence;
  }

  /**
   * Adds a page to the list of pages.
   * @param page Page to add.
   */
  public void addPage(int page) {
    pages.add(page);
  }

  /**
   * @return A representation of this object as a string.
   */
  @Override
  public String toString() {
    return "Process{" +
      "id: " + id +
      ", name: " + name +
      ", state: " + state.toString() +
      ", pages: " + pages.toString() +
      "}";
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }
}
