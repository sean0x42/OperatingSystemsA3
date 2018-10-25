package io.seanbailey.simulator;

import io.seanbailey.simulator.process.Process;

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

  private Frame[] frames;

  /**
   * Constructs a new memory object.
   * @param size Number of frames to add.
   */
  public Memory(int size) {
    frames = new Frame[size];

    // Initialise frames
    for (int i = 0; i < frames.length; i++) {
      frames[i] = new Frame();
    }
  }

  /**
   * Assigns a process with the given number of frames.
   * @param range Number of frames to assign.
   * @param process Process to assign.
   */
  public void assignFrames(int range, Process process) {
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
      frame.setOwningProcess(process);
      added++;
    }
  }
}
