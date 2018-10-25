package io.seanbailey.simulator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Cleanly formats content into a table.
 * @author Sean Bailey c3279343
 */
public class OutputTable {

  private List<Object[]> rows; // A list of rows in the table
  private final int SPACING;   // The number of spaces between each column
  private int width;           // The number of columns (updated automatically when rows are added).

  /**
   * Constructs a new table.
   */
  public OutputTable() {
    this(2);
  }

  /**
   * Constructs a table to be printed by the output handler.
   * @param spacing Column spacing (in spaces).
   */
  @SuppressWarnings("WeakerAccess")
  public OutputTable(int spacing) {
    this.SPACING = spacing;
    this.width = 0;
    this.rows = new ArrayList<>();
  }

  /**
   * Adds a row to the table.
   * @param values A variable number of values, to be added to the row.
   * @return The output table for chaining.
   */
  public OutputTable addRow(Object... values) {
    rows.add(values);
    if (width < values.length) {
      width = values.length;
    }
    return this;
  }

  /**
   * Returns this table as a string. This is useful for printing it to STDOUT or
   * to a text area.
   * @return A string representing the tables contents.
   */
  @Override
  public String toString() {
    // Init
    StringBuilder result = new StringBuilder();
    int[] columnWidths = calculateColumnWidths();
    String spacer = String.format("%" + SPACING + "s", "");

    // Iterate over rows
    for (Object[] row : rows) {
      StringJoiner joiner = new StringJoiner(spacer, "", "\n");

      // Iterate over data in rows
      for (int i = 0; i < row.length; i++) {
        joiner.add(String
            .format("%-" + columnWidths[i] + "s", String.valueOf(row[i])));
      }
      result.append(joiner.toString());
    }

    return result.toString();
  }

  /**
   * @return the width of all columns.
   */
  private int[] calculateColumnWidths() {
    // Init
    int[] widths = new int[this.width];
    for (int i = 0; i < width; i++) {
      widths[i] = 0;
    }

    // Iterate over each item to find the largest one in each column
    for (Object[] row : rows) {
      for (int i = 0; i < row.length; i++) {
        String value = String.valueOf(row[i]);
        if (value.length() > widths[i]) {
          widths[i] = value.length();
        }
      }
    }

    return widths;
  }
}

