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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import output.ErrorOutput;

/**
 * This class is responsible for loading a previously saved mock JShell onto the
 * current mock JShell.
 * 
 * @author Marcus
 *
 */
public class LoadJShell extends Command {

  /**
   * Creates a new LoadJShell object with the commands valid arguments counts
   * and LoadJShell's specific documentation.
   * 
   * @param validArgumentCounts The array of valid argument counts
   */
  public LoadJShell(int[] validArgumentCounts) {
    super(validArgumentCounts);
    this.documentation = "loadJShell FileName"
        + "\n\tLoads the contents of file FileName and renstantites everything"
        + "\n\tthat was previously saved into FileName. This must be the first"
        + "\n\tcommand typed in a new shell.";
  }

  /**
   * If no commands have been put into the shell, and the input is a valid
   * JShell save file, loads previous JShell onto the current one.
   * 
   * @param shellInput An array of arguments for the command.
   * @return The new file system or null if there is an error.
   */
  @Override
  public FileSystem execute(String[] shellInput) {

    String[] arguments = super.getArguments(shellInput);

    // Checks if the number of arguments is valid
    super.execute(arguments);
    if (!isCountValid) {
      return null;
    }

    // Checks if a command has been inputed into the JShell
    if (FileSystem.getInstance().getCommandHistory().size() > 1) {
      ErrorOutput.println("Cannot load JShell onto a modified session");
      return null;
    }

    try {
      // Creates the input streams for the file containing the serialized JShell
      FileInputStream fileIn;
      ObjectInputStream objectIn;
      fileIn = new FileInputStream("savedJShells/" + shellInput[0]);
      objectIn = new ObjectInputStream(fileIn);

      // Sets the current JShell to the saved one
      FileSystem.getInstance()
          .setFileSystem((FileSystem) objectIn.readObject());
      objectIn.close();
      fileIn.close();

    } catch (IOException e) {
      ErrorOutput.println(shellInput[0] + " does not exist.");
      return null;
    } catch (ClassNotFoundException c) {
      ErrorOutput.println("Class not found");
      return null;
    }
    return FileSystem.getInstance();
  }

}
