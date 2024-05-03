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

import filesystem.Directory;
import filesystem.FileSystem;
import handling.PathHandler;

/**
 * The PrintWorkingDirectory class is a Command that displays the full path of
 * the working directory.
 */
public class PrintWorkingDirectory extends OutputCommand {

  /**
   * Constructs a new PrintWorkingDirectory with its valid argument counts and
   * documentation.
   *
   * @param validArgumentCounts Stores all the valid argument counts for
   *        PrintWorkingDirectory.
   */
  public PrintWorkingDirectory(int[] validArgumentCounts) {
    super(validArgumentCounts);
    this.documentation = "pwd"
        + "\n\tPrint the current working directory (including the whole path).";
  }

  /**
   * If the size of arguments is in validArgumentCounts then this displays the
   * full path of workingDirectory and returns it as a string. Otherwise, it
   * displays a an error message and returns a corresponding string similar to
   * the error message.
   *
   * @param shellInput An array of arguments for the command.
   * @return The full path of workingDirectory, or an error message.
   */
  @Override
  public String execute(String[] shellInput) {

    // Splits the arguments of shellInput into an array of arguments
    String[] arguments = super.getArguments(shellInput);

    // Checks if the arguments is a valid argument count
    super.execute(arguments);

    if (!isCountValid || (hasRedirect() && outFile == null)) {
      return "Inv Args";
    }
    
    FileSystem fileSystem = FileSystem.getInstance();
    PathHandler pathHandler = fileSystem.getPathHandler();

    Directory dir = fileSystem.getWorkingDirectory();

    if (dir == null) {
      return "No Working Dir";
    }

    String dirName = pathHandler.getPath(dir);
    handleOutput(dirName);
    return dirName;
  }
}
