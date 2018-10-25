package io.seanbailey.simulator.policy;

import io.seanbailey.simulator.Frame;
import java.util.List;

/**
 * An abstract memory management policy.
 * @author Sean Bailey c3279343
 */
public abstract class Policy {

  private final String shortName;

  /**
   * Constructs a new policy.
   * @param shortName Short, human readable name for this policy.
   */
  public Policy(String shortName) {
    this.shortName = shortName;
  }

  /**
   * Loads a page into the given list of frames.
   * @param frames Memory frames.
   * @param page Page to load.
   * @param time Current simulation time.
   */
  public abstract void loadPage(List<Frame> frames, int page, int time);

  /**
   * Reads a given page from the list of frames.
   * @param frames Memory frames.
   * @param page Page to read.
   * @param time Current simulation time.
   */
  public abstract void read(List<Frame> frames, int page, int time);

  public String getShortName() {
    return shortName;
  }
}
