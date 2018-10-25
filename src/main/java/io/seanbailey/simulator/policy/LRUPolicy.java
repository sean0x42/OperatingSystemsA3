package io.seanbailey.simulator.policy;

import io.seanbailey.simulator.Frame;
import java.util.List;

/**
 * Least Recently Used policy. 
 * @author Sean Bailey c3279343
 */
public class LRUPolicy extends Policy {

  /**
   * Constructs a new LRU policy
   */
  public LRUPolicy() {
    super("LRU");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadPage(List<Frame> frames, int page, int time) {
    Frame leastRecentlyUsed = null;

    // Iterate over each frame
    for (Frame frame : frames) {
      // Check for empty frame
      if (frame.getPage() == null) {
        frame.setPage(page);
        frame.setMeta(time);
        return;
      }

      // Check if this frame has been used "less recently" 
      if (leastRecentlyUsed == null ||
          frame.getMeta() < leastRecentlyUsed.getMeta()) {
        leastRecentlyUsed = frame;
      }
    }

    // Load in page
    leastRecentlyUsed.setPage(page);
    leastRecentlyUsed.setMeta(time);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void read(List<Frame> frames, int page, int time) {
    // Search for appropriate frame
    for (Frame frame : frames) {
      // Set last access time
      if (frame.getPage() == page) {
        frame.setMeta(time);
        return;
      }
    }
  }
}
