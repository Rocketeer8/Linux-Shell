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

import commands.ListContent;
import commands.MakeDirectory;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ListContentTest {

  ListContent lsObj;
  MakeDirectory mkdirObj;
  FileSystem fs;
  
  /**
   * Initialize related field for testing ListContent.
   * @throws Exception  An exception ListContent can throw. 
   */
  @Before
  public void setUp() throws Exception {
    mkdirObj = new MakeDirectory(new int[] {-2});
    fs = FileSystem.getInstance();
    lsObj = new ListContent(new int[] {-1});
  }
  
  /**
   * Clear fileSystem after testing.
   * @throws Exception  An exception ListContent can throw.
   */
  @After
  public void tearDown() throws Exception {
    // reset FileSystem instance to empty after each test
    Field field = (fs.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null); // setting the shellInstance parameter to null
  }
  
  /**
   * test execute() given a file.
   */
  @Test
  public void executeTest() {
    // test if listContent output is correct for current working directory
    mkdirObj.execute(new String[] {"dir1 dir1/dirInsideDir1 dir2"});
    String lsOutput = lsObj.execute(new String[] {});
    assertEquals(lsOutput, "dir1\ndir2\n");

  }
  
  /**
   * test execute() given a path.
   */
  @Test
  public void executeTestWithPath() {
    // test if listContent output is correct for a given directory path
    mkdirObj.execute(new String[] {"dir1 dir1/dirInsideDir1 dir2"});
    String lsOutput = lsObj.execute(new String[] {"/dir1"});
    assertEquals(lsOutput, "dirInsideDir1\n");
  }
  
  /**
   * test execute() for recursive listing.
   */
  @Test
  public void executeTestRecursive() {
    // test if listContent output is correct for recursive listing
    mkdirObj.execute(
        new String[] {"dir1 dir1/dirInsideDir1 dir2 dir2/dirInsideDir2"});
    String lsOutput = lsObj.execute(new String[] {"-R"});
    assertEquals(lsOutput, "dir1\n  dirInsideDir1\ndir2\n  dirInsideDir2\n");

  }

}
