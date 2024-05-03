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
import filesystem.File;
import filesystem.FileObj;
import filesystem.FileSystem;
import handling.InputParser;
import handling.PathHandler;
import handling.StandardError;
import java.util.Arrays;

/**
 * The Search class is a Command that recursively searches if a file or
 * directory is inside given directories.
 *
 * @author Aliel
 */
public class Search extends OutputCommand {

  /**
   * Constructs a new Search with its valid argument counts and documentation.
   *
   * @param validArgumentCounts Stores all the valid argument counts for Search
   */
  public Search(int[] validArgumentCounts) {
    super(validArgumentCounts);
    this.documentation = "search path ... -type [f|d] -name expression"
        + "\n\tSearches the directories path ... of type [f|d] for name "
        + "\n\texpression";
  }

  /**
   * If the size of arguments is in validArgumentCounts and argument has the
   * required arguments, then for each directory given, this displays the paths
   * that contain fileName and directories that do not contain fileName and
   * returns what is displayed as a string. Otherwise, it displays a an error
   * message and returns a corresponding string similar to the error message.
   *
   * @param shellInput An array of arguments for the command.
   * @return The paths that contain fileName, given an array of directories, or
   *         an error message.
   */
  public String execute(String[] shellInput) {
    FileSystem fileSystem = FileSystem.getInstance();
    InputParser parser = fileSystem.getParser();
    StandardError errorHandler = fileSystem.getErrorHandler();

    // Splits the arguments of shellInput into an array of arguments
    String[] arguments = this.getArguments(shellInput);
    int numOfArguments = arguments.length;

    // Checks if the arguments is a valid argument count
    super.execute(arguments);

    if (!(isCountValid && errorHandler.isValidSearchArgs(arguments))
        || (hasRedirect() && outFile == null)) {
      return "Inv Args";
    }

    String fileName =
        parser.parseStringArgumentToString(arguments[numOfArguments - 1]);

    if (fileName == null || !errorHandler.isValidFileName(fileName)) {
      return "Inv Expression";
    }
    String fileType = arguments[numOfArguments - 3];

    // Gives the directory arguments of argument
    return this.search(Arrays.copyOfRange(arguments, 0, arguments.length - 4),
        fileName, fileType);
  }

  /**
   * Checks if fileName of object indicated by fileType is in a directory,
   * including its subdirectories, in directories.
   *
   * @param directories An array of directory names for the command.
   * @param fileName The name of the file or directory to search.
   * @param fileType Indicates the type of object to look for.
   */
  private String search(String[] directories, String fileName,
      String fileType) {
    FileSystem fileSystem = FileSystem.getInstance();
    InputParser parser = fileSystem.getParser();
    PathHandler pathHandler = fileSystem.getPathHandler();
    StringBuilder output = new StringBuilder();

    for (String directory : directories) {
      boolean inDirectory = false;
      Directory searchedDir = parser.parsePathToDirectory(directory);

      if (searchedDir == null) {
        return output.append("Invalid Path").append("\n").toString();
      }

      for (FileObj subFileObj : searchedDir) {
        if (this.matchesRequirements(subFileObj, fileName, fileType)) {
          handleOutput(pathHandler.getPath(subFileObj));
          output.append(pathHandler.getPath(subFileObj)).append("\n");
          inDirectory = true;
        }
      }

      if (!inDirectory) {
        handleOutput(fileName + " not found in " + directory);
        output.append(fileName).append(" not found in ").append(directory)
            .append("\n");
      }
    }

    return output.toString();
  }

  /**
   * Returns true if fileObj is the object indicated by fileType and its name is
   * equal to fileName. Otherwise, return false.
   *
   * @param fileObj The FileObj that is checked.
   * @param fileName The name of the FileObj that is being searched
   * @param fileType Indicates the object type being searched.
   * @return True if fileObj is of object indicated by fileType and its name is
   *         equal to fileName, or false.
   */
  private boolean matchesRequirements(FileObj fileObj, String fileName,
      String fileType) {
    return ((fileType.equals("f") && fileObj instanceof File)
        || (fileType.equals("d") && fileObj instanceof Directory))
        && fileObj.getName().equals(fileName);
  }
}
