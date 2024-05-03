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

package mock;

import filesystem.Directory;
import handling.StandardError;

/**
 * This is a mock class for StandardError, the methods only obey signature and
 * return type, it bypass the implementation.
 * 
 * @author danny
 */
public class MockStandardError extends StandardError {

  private static final long serialVersionUID = 1L;

  /**
   * mock method for isValidFile().
   * 
   * @param parentDir parent directory
   * @param fileName name of the file
   * @return return true for testing
   */
  public boolean isValidFile(Directory parentDir, String fileName) {
    return true;
  }

  /**
   * mock method for isValidFileObj().
   * 
   * @param parentDir parent directory
   * @param fileName name of the file
   * @return return true for testing
   */
  public boolean isValidFileObj(Directory parentDir, String fileName) {
    return true;
  }

  /**
   * mock method for isValidPath().
   * 
   * @param argument argument in string
   * @param fileNum index number of file in the path
   * @return return true for testing
   */
  public boolean isValidPath(String argument, int fileNum) {
    return true;
  }

  /**
   * mock method for isValidPath().
   * 
   * @param argument argument in string
   * @return return true for testing
   */
  public boolean isValidPath(String argument) {
    return true;
  }

  /**
   * mock method for isValidNumber().
   * 
   * @param argument argument in string
   * @return return true for testing
   */
  public boolean isValidNumber(String argument) {
    return true;
  }

  /**
   * mock method for isValidString().
   * 
   * @param argument argument in string
   * @return return true for testing
   */
  public boolean isValidString(String argument) {
    return true;
  }

  /**
   * mock method for isValidCommand().
   * 
   * @param argument argument in string
   * @return return true for testing
   */
  public boolean isValidCommand(String argument) {
    return true;
  }
}
