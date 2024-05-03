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

package test;

import static org.junit.Assert.assertEquals;

import commands.Echo;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EchoTest {

  Echo echoObj;
  FileSystem fs;
  
  /**
   * Initialize related field for testing Echo.
   * @throws Exception  An exception Echo can throw. 
   */
  @Before
  public void setUp() throws Exception {
    echoObj = new Echo(new int[] {1, 2, 3});
    fs = FileSystem.getInstance();
  }
  
  /**
   * Clear fileSystem after testing.
   * @throws Exception  An exception Echo can throw.
   */
  @After
  public void tearDown() throws Exception {
    // reset FileSystem instance to empty after each test
    Field field = (fs.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null); // setting the shellInstance parameter to null
  }
  
  /**
   * Test Execute() with a string.
   */
  @Test
  public void executeTestWithString() {
    // test echo String, string is surround by double quotes
    String output = echoObj.execute(new String[] {"\"Hello World!\""});
    assertEquals(output, "Hello World!");
  }
  
  /**
   * Test Execute() with an invalid string.
   */
  @Test
  public void executeTestWithInvalidString() {
    // test echo String with invalid symbol
    String output = echoObj.execute(new String[] {"\"Hel\"lo Wo@rld!\""});
    assertEquals(output, null);
  }
  
  /**
   * Test Execute() with redirection.
   */
  @Test
  public void executeTestWithRedirection() {
    // test echo String with invalid symbol
    echoObj.execute(new String[] {"\"Hello World!\" > fileA"});
    File madeFile = echoObj.getFile();
    File expcetedFile = (File) fs.getRootDirectory().getSubFile("fileA");
    assertEquals(madeFile, expcetedFile);
  }
}
