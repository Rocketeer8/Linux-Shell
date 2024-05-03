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

import driver.JShell;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileObj;
import filesystem.FileSystem;
import java.io.Serializable;
import java.util.Stack;
import output.ErrorOutput;

/**
 * The StandardError class is responsible for checking if various input
 * arguments are valid, and printing a specific error message accordingly.
 * 
 * @author Marcus
 *
 */

public class StandardError implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * The error handlers path handler for navigating the paths provided by input.
   */
  private PathHandler pathHandler;


  /**
   * Creates a new StandardError object and initialized the pathHandler.
   */
  public StandardError() {
    pathHandler = new PathHandler();
  }

  /**
   * Returns a boolean based on if validArguemntCounts contains the size of
   * shellInput. If an element in validArguemntCounts is positive, that element
   * is a valid argument count. If an element in validArguemntCounts is
   * negative, then (-1) * element - 1 is the lower bound for a valid argument
   * count.
   * 
   * @param validArgumentCounts The array of valid argument counts.
   * @param shellInput The array of input from the shell.
   * @return A boolean, true if the count of shellInput is valid, and false if
   *         otherwise.
   */
  public boolean checkArgumentCount(int[] validArgumentCounts,
      String[] shellInput) {
    int argumentcount = shellInput.length;

    for (int validArgumentCount : validArgumentCounts) {
      // Checks if the number of arguments is in the array of valid counts
      if (validArgumentCount == argumentcount) {
        return true;
      }
      // Checks if the number of arguments is greater than the lower bound
      // specified by the negative number
      if (validArgumentCount < 0) {
        if (argumentcount >= -1 * (validArgumentCount + 1)) {
          return true;
        }
      }
    }
    ErrorOutput.println("Invalid argument count");
    return false;

  }

  /**
   * Returns true if a file object with name argument already exists in
   * parentDir, and returns false otherwise.
   * 
   * @param parentDir The directory to check if name exists in.
   * @param name The name of the file object to check.
   * @return A boolean, true if a file object with name exists, and false
   *         otherwise.
   */
  public boolean isDuplicateName(Directory parentDir, String name) {

    if (parentDir.getSubFile(name) != null) {
      ErrorOutput.println(name + " already exists");
      return true;
    }
    return false;

  }


  /**
   * Returns true if argument is a valid number, and returns false otherwise. In
   * other words, checks if argument can be parsed into a non-negative integer.
   * 
   * @param argument The input argument as a string.
   * @return A boolean, true if argument is a valid number, and false otherwise.
   */
  public boolean isValidNumber(String argument) {

    int parsedString = -1;
    // Checks if argument is an integer
    try {
      parsedString = Integer.parseInt(argument);
    } catch (Exception e) {
      ErrorOutput.println("Invalid number");
      return false;
    }

    // Checks if argument is a positive integer
    if (parsedString < 0) {
      ErrorOutput.println("Invalid number");
      return false;
    }
    return true;
  }


  /**
   * Returns true if argument is a valid command name, and returns false
   * otherwise. In other words, checks if a command with name argument exists.
   * 
   * @param argument The input argument as a string.
   * @return A boolean, true if a command with name "argument" exists in the
   *         shell, and false otherwise.
   */
  public boolean isValidCommand(String argument) {

    // Checks if argument is a command in the file system
    if (!JShell.getCommands().containsKey(argument)) {
      ErrorOutput.println("Invalid command");
      return false;
    }

    return true;
  }


  /**
   * Returns true if argument is a valid string, and returns false otherwise. In
   * other words, checks if argument is surrounded by a single quotation on each
   * end, and contains no quotations inside.
   * 
   * @param argument The argument as a string.
   * @return A boolean, true if argument is a valid string, and false otherwise.
   */
  public boolean isValidString(String argument) {

    boolean temp = true;
    if (argument.length() <= 1) {
      temp = false;
    }

    if (Character.compare(argument.charAt(0), '\"') != 0 || Character
        .compare(argument.charAt(argument.length() - 1), '\"') != 0) {
      temp = false;
    }

    if (!temp || argument.substring(1, argument.length() - 1).contains("\"")) {
      ErrorOutput.println("Invalid string");
      return false;
    }

    return true;
  }


  /**
   * Returns true if argument is a valid file name, and returns false otherwise.
   * In other words, checks if argument contains a character from
   * "/.!@#$%^&*(){}~|<>?".
   * 
   * @param argument The argument as a string. @return, A boolean, true if
   *        argument is a valid file name, and false otherwise.
   */
  public boolean isValidFileName(String argument) {

    String invalidChars = "/.!@#$%^&*(){}~|<>?\"";

    for (int i = 0; i < invalidChars.length(); i++) {
      if (argument.indexOf(invalidChars.charAt(i)) != -1) {
        ErrorOutput.println(argument + " is an invalid file name");
        return false;
      }
    }

    return true;
  }

  /**
   * Returns true if argument is a valid path, and returns false otherwise. In
   * other words, checks if path can be read, and leads to an existent
   * directory.
   * 
   * @param argument The argument as a string.
   * @return A boolean, true if argument is a valid path, and false otherwise.
   */
  public boolean isValidPath(String argument) {

    return isValidPath(argument, -1);
  }

  /**
   * Returns true if argument is a valid path, and returns false otherwise. In
   * other words, checks if path can be read, and leads to an existent
   * directory. Also checks if there are multiple slashes, and returns false if
   * there is.
   * 
   * @param argument The argument as a string.
   * @param fileNum file number to end traversal, -1 means traverse the whole
   *        path, -2 means traverse until the second last file item on the path,
   *        etc
   * @return A boolean, true if argument is a valid path, and false otherwise.
   */
  public boolean isValidPath(String argument, int fileNum) {

    if (pathHandler.traverse(argument, fileNum) == null) {
      ErrorOutput.println("Invalid path");
      return false;
    }

    for (int i = 0; i < argument.length() - 1; i++) {
      if (argument.charAt(i) == '/' && argument.charAt(i + 1) == '/') {
        ErrorOutput.println("Invalid path");
        return false;
      }
    }

    return true;
  }

  /**
   * Given a directory and a name, check if the directory has such sub file from
   * the name. The sub file can be both a file or a directory.
   * 
   * @param parentDir Directory to check the sub files on.
   * @param fileName The name to check if the file exist.
   * @return return true if such file exist, false if not.
   */
  public boolean isValidFileObj(Directory parentDir, String fileName) {

    // make sure parentDir error message is handled before this method
    if (parentDir == null) {
      return false;
    }

    FileObj fo = parentDir.getSubFile(fileName);
    // If fileObj doesn't exist, print out error message
    if (fo == null) {
      ErrorOutput.println("Invalid path");
      return false;
    }
    return true;
  }

  /**
   * Given a directory and a name, check if the name one of the directory's
   * subfile. Also the subfile have to be a file, not a directory.
   * 
   * @param parentDir Directory to check the sub files on.
   * @param fileName The name to check if the file exist.
   * @return return true if such file exist, false if not.
   */
  public boolean isValidFile(Directory parentDir, String fileName) {

    // make sure parentDir error message is handled before this method
    if (parentDir == null) {
      return false;
    }
    if (!(parentDir.getSubFile(fileName) instanceof File)) {
      ErrorOutput.println("Invalid path");
      return false;
    }
    return true;

  }

  /**
   * Returns true if argument stack is empty, and returns false otherwise.
   * 
   * @param stack The possibly empty stack.
   * @return A boolean, true if stack is empty, and false otherwise.
   */
  public boolean isStackEmpty(Stack<Directory> stack) {

    if (stack.isEmpty()) {
      ErrorOutput.println("Stack is empty");
      return true;
    }
    return false;

  }

  /**
   * Returns true if arguments has the required arguments for the search
   * command, and is in correct order. Returns false otherwise.
   *
   * @param arguments An array of arguments for the command.
   * @return True if arguments has, in order, the required argument for the
   *         search command, or false otherwise.
   */
  public boolean isValidSearchArgs(String[] arguments) {
    int numOfArguments = arguments.length;

    // All calls for search must have these arguments at the specific indices
    if (numOfArguments < 5) {
      System.out.println("Argument count is too small");
      return false;
    } else if (!arguments[numOfArguments - 4].equals("-type")) {
      System.out.println("\"-type\" is missing");
      return false;
    } else if (!(arguments[numOfArguments - 3].equals("f")
        || arguments[numOfArguments - 3].equals("d"))) {
      System.out.println("[\"f\"|\"d\"] is missing");
      return false;
    } else if (!arguments[numOfArguments - 2].equals("-name")) {
      System.out.println("\"-name\" is missing");
      return false;
    } else if (!arguments[numOfArguments - 1].startsWith("\"")
        || !arguments[numOfArguments - 1].endsWith("\"")) {
      System.out.println("Missing \" symbol(s) at start/end of expression");
      return false;
    }

    return true;
  }

  /**
   * Check if a fileObj can legally move to another directory. Return true if it
   * can, false otherwise
   *
   * @param itemToMove The fileObj to be moved
   * @param destination The directory the fileObj will move to
   * @return True if itemTomove can be moved legally, false otherwise
   */
  public boolean isValidMove(FileObj itemToMove, Directory destination) {
    if (itemToMove == null || destination == null) {
      return false;
    }
    FileSystem fileSystem = FileSystem.getInstance();
    boolean valid = true;

    if (itemToMove.equals(fileSystem.getRootDirectory())
        || itemToMove.equals(fileSystem.getWorkingDirectory())
        || itemToMove.getParentDirectory().equals(destination)) {
      /*
       * check if itemToMove is root or working directory or moving to the same
       * directory
       */
      valid = false;
    }
    Directory tempParentDir = destination.getParentDirectory();
    while (tempParentDir != null) {
      // check if destination's parent directory contain itemToMove
      if (tempParentDir.equals(itemToMove)) {
        valid = false;
      }
      tempParentDir = tempParentDir.getParentDirectory();
    }
    for (String fileName : destination.subFileNameList()) {
      // check if contain the same name
      if (itemToMove.getName().equals(fileName)) {
        valid = false;
      }
    }
    if (valid == false) {
      ErrorOutput.println("Invalid Move");
    }
    return valid;
  }
}
