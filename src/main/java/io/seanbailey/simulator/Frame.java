package io.seanbailey.simulator;

import io.seanbailey.simulator.process.Process;

/**
 * Represents a single frame in memory.
 * @author Sean Bailey c3279343
 */
public class Frame {
  
  private Process process; // Process which owns this frame 
  private Integer page;    // Currently loaded page
  private int meta;        // Meta data. i.e. use bit in clock policy, last used for LRU

  public Frame() {
    process = null;
    page = null;
    meta = Integer.MIN_VALUE;
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
}
