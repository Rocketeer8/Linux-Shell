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

import commands.Concatenate;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is responsible for testing the Concatenate class.
 * 
 * @author Marcus
 */
public class ConcatenateTest {

  /** The FileSystem instance used for testing. */
  private FileSystem fileSystem;
  /** The Concatenate instance to be tested. */
  private Concatenate cat;

  /**
   * Initializes fileSystem and cat.
   * 
   * @throws Exception An exception Concatenate can throw.
   */
  @Before
  public void setUp() throws Exception {
    fileSystem = FileSystem.getInstance();
    cat = new Concatenate(new int[] {-2});
  }

  /**
   * Sets the singleton fileSystem object to null, for the purpose of testing
   * again.
   * 
   * @throws Exception An exception Concatenate can throw.
   */
  @After
  public void tearDown() throws Exception {
    Field field = (fileSystem.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null);
  }

  /**
   * Tests execute with an invalid argument count.
   */
  @Test
  public void executeTestInvalidArgumentCount() {
    assertEquals("Invalid arguments", cat.execute(new String[] {}));
  }

  /**
   * Tests execute with a nonexistent file.
   */
  @Test
  public void executeTestFileDoesNotExist() {
    assertEquals("Invalid file\n\n", cat.execute(new String[] {"file1"}));
  }

  /**
   * Tests execute with a single file.
   */
  @Test
  public void executeTestSingleFile() {
    File file = new File("file1", "file content");
    fileSystem.getWorkingDirectory().addToDir(file);
    assertEquals("file1:\nfile content\n", cat.execute(new String[] {"file1"}));
  }

  /**
   * Tests execute with a single directory (which should not work for cat).
   */
  @Test
  public void executeTestSingleDirectory() {
    Directory dir = new Directory("dir1");
    fileSystem.getWorkingDirectory().addToDir(dir);
    assertEquals("Invalid file\n\n", cat.execute(new String[] {"dir1"}));
  }

  /**
   * Tests execute with multiple valid files.
   */
  @Test
  public void executeTestMultipleValidFiles() {
    File file1 = new File("file1", "a");
    File file2 = new File("file2", "b");
    fileSystem.getWorkingDirectory().addToDir(file1);
    fileSystem.getWorkingDirectory().addToDir(file2);
    String expected = "file1:\na\n\n\nfile2:\nb\n";
    assertEquals(expected, cat.execute(new String[] {"file1 file2"}));
  }

  /**
   * Tests execute with multiple valid files followed by an invalid file.
   */
  @Test
  public void executeTestMultipleValidFilesFollowedByInvalidFile() {
    File file1 = new File("file1", "a");
    fileSystem.getWorkingDirectory().addToDir(file1);
    String expected = "file1:\na\nInvalid file\n\n";
    assertEquals(expected, cat.execute(new String[] {"file1 file2"}));

    File file3 = new File("file3", "b");
    fileSystem.getWorkingDirectory().addToDir(file3);
    assertEquals(expected, cat.execute(new String[] {"file1 file2 file3"}));

  }


}
