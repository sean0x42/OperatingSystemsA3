package io.seanbailey.simulator.process;

/**
 * An enum which represents the current state of a
 * @link{io.seanbailey.simulator.process.Process}.
 * @author Sean Bailey c3279343
 */
public enum State {
  READY,
  RUNNING,
  BLOCKED,
  EXIT
}
