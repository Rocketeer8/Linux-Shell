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

package output;

/**
 * This class is used to output messages to the shell. This class is the parent
 * of ErrorOutput class.
 * 
 * @author danny
 */
public class Output {
  StringBuilder output = new StringBuilder("");

  public void storeOutput(String output) {
    this.output.append(output);
  }
  
  /**
   * Get the stored output.
   * 
   * @return  return the stored output
   */
  public String releaseStoredOutput() {
    String resultOutput = this.output.toString();
    this.output.setLength(0);
    return resultOutput;
  }

  /**
   * Output to shell. Print a new line after.
   * 
   * @param str A string to be output on the shell.
   */
  public static void println(String str) {
    System.out.println(str);
  }

  /**
   * Output to shell without printing a new line after.
   * 
   * @param str A string to be output on the shell.
   */
  public static void print(String str) {
    System.out.print(str);
  }

}
