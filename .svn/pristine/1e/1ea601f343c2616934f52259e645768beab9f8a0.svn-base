// **********************************************************
// Assignment2:
// Student1: Marcus Pasquariello
// UTORID user_name: pasqua39
// UT Student #: 1006258477
// Author: Marcus Pasquariello
//
// Student2: Aliel Jacob Roxas
// UTORID user_name: roxasal1
// UT Student #: 1005954781
// Author: Aliel Jacob Roxas
//
// Student3: Danny Liu
// UTORID user_name: liuhai6
// UT Student #: 1004258000
// Author: Danny Liu
//
// Student4: Brandon Lam
// UTORID user_name: lambran3
// UT Student #: 1003383484
// Author: Brandon Lam
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package commands;

import filesystem.FileSystem;
import handling.InputParser;
import java.util.ArrayList;

/**
 * The History class is a Command that displays recent user commands.
 *
 * @author Aliel
 */
public class History extends OutputCommand {

  /**
   * Constructs a new History with its valid argument counts and documentation.
   *
   * @param validArgumentCounts Stores all the valid argument counts for
   *        History.
   */
  public History(int[] validArgumentCounts) {
    super(validArgumentCounts);
    this.documentation = "history [number]"
        + "\n\tThis command will print out recent commands, one command per "
        + "\n\tline. The first column is numbered such that the line with the "
        + "\n\thighest number is the most recent command. We can truncate the "
        + "\n\toutput by specifying a number (>=0) after the command.";
  }

  /**
   * If the size of arguments is in validArgumentCount, then it displays recent
   * user commands and returns it as a string. The output is truncated by giving
   * a number >= 0 as an argument. Otherwise, it displays a an error message and
   * returns a corresponding string similar to the error message.
   *
   * @param shellInput An array of arguments for the command.
   * @return The string of commands from history that may be truncated, or an
   *         error message.
   */
  @Override
  public String execute(String[] shellInput) {
    FileSystem fileSystem = FileSystem.getInstance();
    InputParser parser = fileSystem.getParser();
    ArrayList<String> history = fileSystem.getCommandHistory();
    int startIndex = 0;

    // Splits the arguments of shellInput into an array of arguments
    String[] arguments = super.getArguments(shellInput);

    // Checks if arguments is a valid argument count
    super.execute(arguments);

    if (!isCountValid || (hasRedirect() && outFile == null)) {
      return "Inv Args";
    } else if (arguments.length == 1) {
      // Checks if argument is a valid number and returns it as an integer
      int displayNum = parser.parseNumberArgumentToNumber(arguments[0]);

      if (displayNum < 0) {
        return "Inv Num";
      }

      // Returns the starting index of the loop
      startIndex = this.getStartIndex(displayNum, history.size());
    }

    // Builds the string that is outputted
    return this.printHistory(history, startIndex);
  }

  /**
   * Return the starting index for a truncated output displaying the last
   * displayNum commands. Return 0 if displayNum is greater then historySize.
   *
   * @param displayNum The number of recent commands to display.
   * @param historySize The size of the user's command history.
   * @return The starting index for an output displaying the last displayNum
   *         commands, or 0 if displayNum is greater than historySize.
   */
  private int getStartIndex(int displayNum, int historySize) {

    // If displayNum is greater, then it displays all recent commands
    if (displayNum > historySize) {
      return 0;
    }

    // To truncate, startIndex starts displayNum from historySize
    return historySize - displayNum;
  }

  /**
   * Returns a string of commands from history from startIndex. Otherwise,
   * return null;
   *
   * @param history The list of called user commands.
   * @param startIndex The index where to start printing the commands.
   * @return A string of commands from history starting at start index, or null.
   */
  private String printHistory(ArrayList<String> history, int startIndex) {
    if (history.isEmpty()) {
      return null;
    }

    StringBuilder output = new StringBuilder();

    for (int i = startIndex; i < history.size(); i++) {
      output.append(i + 1).append(". ").append(history.get(i));

      if (i + 1 < history.size()) {
        output.append("\n");
      }
    }

    handleOutput(output.toString());
    return output.toString();
  }
}
