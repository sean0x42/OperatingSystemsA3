package io.seanbailey.simulator.process;

import io.seanbailey.simulator.process.State;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Represents a single process, to be handled by scheduling algorithms.
 * @author Sean Bailey c3279343
 */
public class Process {

  // Use to keep track of the current process id,
  private static int idSequence = 0;

  // Final variables
  private LinkedList<Integer> pages = new LinkedList<>();
  private final int id;
  private final String name;

  private State state = State.READY;
  private int faults = 0;
  private List<Integer> faultTimes = new ArrayList<>();
  private int turnaroundTime = -1;

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
   * Constructs a new process.
   * @param process Process to clone.
   */
  public Process(Process process) {
    name = process.getName();
    id = process.getId();
    pages = new LinkedList<>(process.getPages());
  }

  /**
   * Adds a page to the list of pages.
   * @param page Page to add.
   */
  public void addPage(int page) {
    pages.offer(page);
  }

  /**
   * @return Whether this process has any more pages left.
   */
  public boolean hasNextPage() {
    return !pages.isEmpty();
  }

  /**
   * Retrieves the next page from the queue.
   * @return the next page in the queue.
   */
  public int getNextPage() {
    return pages.peek();
  }

  /**
   * Removes the head of the page queue.
   */
  public void removePage() {
    pages.remove();
  }

  /**
   * Adds a page fault.
   * @param time Time that the page fault occured.
   */
  public void addFault(int time) {
    faults++;
    faultTimes.add(time);
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
      ", faults: " + faults +
      ", faultTimes: " + faultTimes.toString() +
      ", turnaroundTime: " + turnaroundTime +
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

  public LinkedList<Integer> getPages() {
    return pages;
  }

  public int getTurnaroundTime() {
    return turnaroundTime;
  }

  public void setTurnaroundTime(int time) {
    turnaroundTime = time;
  }

  public int getFaults() {
    return faults;
  }

  public List<Integer> getFaultTimes() {
    return faultTimes;
  }

  public String getFaultTimesString() {
    StringJoiner joiner = new StringJoiner(", ", "{", "}");

    for (int time : faultTimes) {
      joiner.add(String.valueOf(time));
    }

    return joiner.toString();
  }
}
