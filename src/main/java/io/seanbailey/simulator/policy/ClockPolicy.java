package io.seanbailey.simulator.policy;

import io.seanbailey.simulator.Frame;
import io.seanbailey.simulator.util.Logger;
import java.util.List;

/**
 * A type of policy, which uses frames like a circular buffer.
 * @author Sean Bailey c3279343
 */
public class ClockPolicy extends Policy {

  private static final Logger logger = new Logger();
  private int buffer = 0;

  /**
   * Constructs a new instance.
   */
  public ClockPolicy() {
    super("Clock");
  }

  /**
   * {@inheritDoc}
   */
  public void loadPage(List<Frame> frames, int page, int time) {
    do {
      // Get current frame
      logger.debug("Buffer: %d", buffer);
      Frame frame = frames.get(buffer);

      // Check for empty frame
      if (frame.getPage() == null) {
        frame.setPage(page);
        frame.setMeta(1);
        buffer = (buffer + 1) % frames.size();
        return;
      }

      // Handle use bit
      if (frame.getMeta() == 1) {
        frame.setMeta(0);
      } else {
        frame.setPage(page);
        frame.setMeta(1);
        buffer = (buffer + 1) % frames.size();
        return;
      }

      // Increment and return to start if necessary
      buffer = (buffer + 1) % frames.size();
    } while(true); // We'll break out manually
  }

  /**
   * {@inheritDoc}
   */
  public void read(List<Frame> frames, int page, int time) {
    // Search for appropriate frame
    for (Frame frame : frames) {
      if (frame.getPage() != null && frame.getPage() == page) {
        frame.setMeta(1); // Set use bit
        return;
      }
    }
  }
}
