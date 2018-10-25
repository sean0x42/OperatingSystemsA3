package io.seanbailey.simulator;

import io.seanbailey.simulator.process.Process;

/**
 * Represents a single frame in memory.
 * @author Sean Bailey c3279343
 */
public class Frame {
  
  private static int idSequence = 0;
  
  private final int id;
  private Process process; // Process which owns this frame 
  private Integer page;    // Currently loaded page
  private int meta;        // Meta data. i.e. use bit in clock policy, last used for LRU

  /**
   * Constructs a new frame.
   */
  public Frame() {
    id = idSequence++;
    process = null;
    page = null;
    meta = Integer.MIN_VALUE;
  }

  /**
   * @return A representation of this frame as a string.
   */
  @Override
  public String toString() {
    return "Frame{" +
      "id: " + id +
      ", process: " + process +
      ", page: " + page +
      ", meta: " + meta +
      "}";
  }

  public int getId() {
    return id;
  }

  public Process getOwningProcess() {
    return process;
  }

  public void setOwningProcess(Process process) {
    this.process = process;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getMeta() {
    return meta;
  }

  public void setMeta(int meta) {
    this.meta = meta;
  }
}
