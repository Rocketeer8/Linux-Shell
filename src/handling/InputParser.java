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

package handling;

import commands.Command;
import driver.JShell;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileObj;
import filesystem.FileSystem;
import java.io.Serializable;
import output.ErrorOutput;

public class InputParser implements Serializable {

  private static final long serialVersionUID = 1L;
  /** The parsers error handler for determining if the input can be parsed. */
  private StandardError errorHandler;
  /** The parsers path handler for navigating the paths provided by input. */
  private PathHandler pathHandler;


  /**
   * Creates a new InputParser and initializes its error handler and path
   * handler.
   */
  public InputParser(StandardError errorHandler, PathHandler pathHandler) {
    this.errorHandler = errorHandler;
    this.pathHandler = pathHandler;
  }

  /**
   * Returns the file at the path argument if the path is valid and the file
   * exists, and returns null otherwise.
   * 
   * @param argument The full or relative path to the file.
   * @return The file if the path is valid and the file exists, and null
   *         otherwise.
   */
  public File parsePathToFile(String argument) {

    Directory tempDirectory = parsePathToSecondLastDirectory(argument);
    if (tempDirectory == null) {
      return null;
    }

    String pathFromLast = parsePathToName(argument);
    FileObj intendedFile = null;

    if (errorHandler.isValidFile(tempDirectory, pathFromLast)) {
      intendedFile = tempDirectory.getSubFile(pathFromLast);
      return (File) intendedFile;
    }

    return null;
  }

  /**
   * Returns the file object at the path argument if the path is valid and the
   * file object exists, and returns null otherwise.
   * 
   * @param argument The full or relative path to the file object.
   * @return The file object if the path is valid and the file object exists,
   *         and null otherwise.
   */
  public FileObj parsePathToFileObj(String argument) {
    /*
     * If path is only one level deep(in current directory or root directory),
     * return that directory instead.
     */
    FileSystem fileSystem = FileSystem.getInstance();
    Directory parentDir = null;

    if (pathHandler.getLevel(argument) == 1) {

      if (argument.substring(0, 1).equals("/")) {
        return fileSystem.getRootDirectory();
      } else {
        parentDir = fileSystem.getWorkingDirectory();
      }

    } else {
      parentDir = parsePathToSecondLastDirectory(argument);
      if (parentDir == null) {
        return null;
        // if parent is root, then parent is the file object to return
      }
    }

    String fileName = parsePathToName(argument);

    if (errorHandler.isValidFileObj(parentDir, fileName)) {
      FileObj fileObj = parentDir.getSubFile(fileName);
      return fileObj;
    }
    return null;
  }


  /**
   * Returns the directory at the path argument if the path is valid and the
   * directory exists, and returns null otherwise.
   * 
   * @param argument The full or relative path to the directory.
   * @return The directory if the path is valid and the directory exists, and
   *         null otherwise.
   */
  public Directory parsePathToDirectory(String argument) {

    if (errorHandler.isValidPath(argument)) {
      return (Directory) pathHandler.traverse(argument, -1);
    }

    return null;

  }

  /**
   * Returns the parent directory to the file object specified in the full or
   * relative path if the path is valid, and returns null otherwise. Note that
   * the last item in the list need not exist in the parent directory.
   * 
   * @param argument The full or relative path to the possible file object.
   * @return The parent directory if the path is valid and null otherwise.
   */
  public Directory parsePathToSecondLastDirectory(String argument) {

    FileSystem fileSystem = FileSystem.getInstance();
    int pathLength = argument.length();

    if (!errorHandler.isValidPath(argument, -2)) {
      return null;
    }

    if (argument.equals("/")) {
      ErrorOutput.println("Invalid path");
      return null;
    }

    if (argument.length() > 1) {
      if (argument.charAt(pathLength - 1) == '/'
          && argument.charAt(pathLength - 2) == '/') {
        ErrorOutput.println("Invalid path");
        return null;
      }
    }

    if (pathHandler.getLevel(argument) == 1) {
      if (argument.substring(0, 1).equals("/")) {
        return fileSystem.getRootDirectory();
      } else {
        return fileSystem.getWorkingDirectory();
      }
    }
    return (Directory) pathHandler.traverse(argument, -2);
  }


  /**
   * Returns the name of the file object specified by path argument.
   * 
   * @param argument The full or relative path to the file object.
   * @return The name of the file object.
   */
  public String parsePathToName(String argument) {

    return pathHandler.getName(argument, -1);

  }


  /**
   * If argument can be parsed into a non negative integer, returns that
   * integer, otherwise returns -1.
   * 
   * @param argument The number as a string.
   * @return The parsed number.
   */
  public int parseNumberArgumentToNumber(String argument) {

    if (this.errorHandler.isValidNumber(argument)) {
      return Integer.parseInt(argument);
    } else {
      return -1;
    }

  }

  /**
   * If argument is a valid string, returns the parsed string (with the
   * quotations removed), otherwise returns null.
   * 
   * @param argument The string argument as a string.
   * @return The parsed string.
   */
  public String parseStringArgumentToString(String argument) {
    argument = argument.trim();
    if (argument.length() == 0) {
      return argument;
    }

    if (this.errorHandler.isValidString(argument)) {
      if (argument.length() == 1) {
        return argument;
      }
      if (argument.startsWith("\"") && argument.endsWith("\"")) {
        return argument.substring(1, argument.length() - 1);
      } else {
        return argument;
      }
    } else {
      return null;
    }

  }

  /**
   * If argument is a valid command name, returns the command, otherwise returns
   * null.
   * 
   * @param argument The command name.
   * @return The parsed command.
   */
  public Command parseCommandArgumentToCommand(String argument) {

    if (this.errorHandler.isValidCommand(argument)) {

      return JShell.getCommands().get(argument);
    } else {
      return null;
    }

  }

}
