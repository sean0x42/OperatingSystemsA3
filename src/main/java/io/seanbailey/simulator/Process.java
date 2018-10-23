package io.seanbailey.simulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single process, to be handled by scheduling algorithms.
 * @author Sean Bailey c3279343
 */
public class Process {

  private static int idSequence = 0;

  private List<Integer> pages;
  private int id;

  /**
   * Constructs a new process.
   */
  public Process() {
    pages = new ArrayList<>();

    // Increment id
    idSequence++;
    id = idSequence;
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
      ", pages: " + pages.toString() +
      "}";
  }

  public int getId() {
    return id;
  }
}
