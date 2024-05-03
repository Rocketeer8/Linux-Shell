package commands;

import filesystem.FileObj;
import filesystem.FileSystem;

/**
 * This class is responsible for displaying the contents of the entire file
 * system on the JShell.
 * 
 * @author Marcus
 *
 */
public class Tree extends OutputCommand {

  /**
   * Creates a new Tree object with the commands valid arguments counts and
   * Tree's specific documentation.
   * 
   * @param validArgumentCounts The array of valid argument counts
   */
  public Tree(int[] validArgumentCounts) {
    super(validArgumentCounts);
    this.documentation = "tree"
        + "\n\tFrom the root directory (‘\\’) display the entire file system "
        + "\n\tas a tree.";
  }

  /**
   * Prints the contents of the entire file system on the JShell.
   * 
   * @param shellInput The array of command arguments
   * @return The tree as a string
   */
  @Override
  public String execute(String[] shellInput) {

    String[] arguments = super.getArguments(shellInput);

    // Checks if the number of arguments is valid
    super.execute(arguments);
    if (!isCountValid || (hasRedirect() && outFile == null)) {
      return "Invalid arguments";
    }

    StringBuilder output = new StringBuilder();
    FileSystem fileSystem = FileSystem.getInstance();

    // Prints the root directory
    output.append("/\n");

    // Uses the root's iterator to print every file and directory in the file
    // system, and with indentation according to depth from root
    for (FileObj subFileObj : fileSystem.getRootDirectory()) {
      for (int i = 0; i < subFileObj.getDepth(); i++) {
        output.append("   ");
      }
      output.append(subFileObj.getName() + "\n");
    }
    handleOutput(output.toString());
    return output.toString();

  }

}
