package io.seanbailey.simulator;

import io.seanbailey.simulator.policy.Policy;
import io.seanbailey.simulator.process.Process;
import io.seanbailey.simulator.util.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Main memory. 
 *
 * <p>
 * Contains a given number of @link{io.seanbailey.simulator.Frame frames}, which
 * can be assigned to various different
 * @link{io.seanbailey.simulator.Process processes}.
 * </p>
 *
 * @author Sean Bailey c3279343
 */
public class Memory {

  private static final Logger logger = new Logger();
  private Frame[] frames;
  private final Policy policy;

  /**
   * Constructs a new memory object.
   * @param policy Memory management policy.
   * @param size Number of frames to add.
   */
  public Memory(Policy policy, int size) {
    this.policy = policy;
    frames = new Frame[size];

    // Initialise frames
    for (int i = 0; i < frames.length; i++) {
      frames[i] = new Frame();
    }
  }

  /**
   * Checks any frames allocated to the given process, to determine if the given
   * page is currently loaded.
   * @param process Owning process.
   * @param page Page to search for.
   * @return Whether the page is loaded by this process.
   */
  public boolean isPageLoaded(Process process, Integer page) {
    for (Frame frame : frames) {
      if (frame.getOwningProcess() == process && frame.getPage() == page) {
        return true;
      }
    }

    return false;
  }

  /**
   * Loads the given page according to the policy.
   * @param process Owning process.
   * @param page Page to load.
   * @param time Current simulation time.
   */
  public void loadPage(Process process, int page, int time) {
    policy.loadPage(getOwnedFrames(process), page, time);
  }

  /**
   * Read a page from main memory.
   * @param process Owning process.
   * @param page Page to read.
   * @param time Current simulation time
   */
  public void read(Process process, int page, int time) {
    policy.read(getOwnedFrames(process), page, time);
  }

  /**
   * Creates a list of all frames owned by a given process.
   * @param process Owning process.
   * @return A list of frames owned by process.
   */
  private List<Frame> getOwnedFrames(Process process) {
    // Init
    List<Frame> frames = new ArrayList<>();

    // Add relevant frames
    for (Frame frame : this.frames) {
      if (frame.getOwningProcess() == process) {
        frames.add(frame);
      }
    }

    return frames;
  }

  /**
   * Assigns a process with the given number of frames.
   * @param range Number of frames to assign.
   * @param process Process to assign.
   */
  public void assignFrames(int range, Process process) {
    // Init
    int added = 0;

    for (Frame frame : frames) {
      // Make sure we haven't reached the cap
      if (added == range) {
        return;
      }

      // Ensure that this frame hasn't already been assigned a process
      if (frame.getOwningProcess() != null) {
        continue;
      }

      // Assign process
      logger.debug("Assigning frame %s to process %s", frame, process.getId());
      frame.setOwningProcess(process);
      added++;
    }
  }
}
