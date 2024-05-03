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
import handling.StandardError;
import java.util.Stack;

/**
 * The PopDirectory class is a Command that pops the most recent directory in
 * the directory stack and sets it as the new working directory by
 * ChangeDirectory.
 *
 * @author Aliel
 */
public class PopDirectory extends Command {

  /**
   * Constructs a new PopDirectory with its valid argument counts and
   * documentation.
   *
   * @param validArgumentCounts Stores all the valid argument counts for
   *        PopDirectory
   */
  public PopDirectory(int[] validArgumentCounts) {
    super(validArgumentCounts);
    this.documentation = "popd"
        + "\n\tRemove the top entry from the directory stack, and cd into it.";
  }

  /**
   * If the size of arguments is in validArgumentCounts and directoryStack is
   * not empty then this pops the most recent Directory in directoryStack and
   * uses ChangeDirectory to make that Directory the new workingDirectory. Then
   * returns that Directory. Otherwise, returns null.
   *
   * @param shellInput An array of arguments for the command.
   * @return The directory popped from the directory stack, or null.
   */
  @Override
  public Directory execute(String[] shellInput) {
   
    FileSystem fileSystem = FileSystem.getInstance();
    StandardError errorHandler = fileSystem.getErrorHandler();
    Stack<Directory> dirStack = fileSystem.getDirectoryStack();
    String[] poppedDirName = null;
    Directory poppedDir = null;

    // Splits the arguments of shellInput into an array of arguments
    String[] arguments = super.getArguments(shellInput);

    // Checks if the arguments is a valid argument count
    super.execute(arguments);

    if (!isCountValid || errorHandler.isStackEmpty(dirStack)) {
      return null;
    }
    
    ChangeDirectory cd = new ChangeDirectory(new int[] {1});

    // If the popped directory does not exist, it goes to the next dir
    while (poppedDirName == null && !errorHandler.isStackEmpty(dirStack)) {
      poppedDir = dirStack.pop();
      poppedDirName = this.checkValidDir(poppedDir);
    }

    if (poppedDirName == null) {
      return null;
    }

    cd.execute(poppedDirName);
    return poppedDir;
  }

  /**
   * Returns path of dir if it exists in the file system. Otherwise, calls an
   * error and return null.
   *
   * @param dir The directory that is checked
   * @return The path of dir if is in the file system, or null.
   */
  private String[] checkValidDir(Directory dir) {
    FileSystem fileSystem = FileSystem.getInstance();
    PathHandler pathHandler = fileSystem.getPathHandler();

    try {
      return new String[] {pathHandler.getPath(dir)};
    } catch (NullPointerException e) {
      // System.out.println(dir.getName() + " no longer exits");
      return null;
    }
  }
}
