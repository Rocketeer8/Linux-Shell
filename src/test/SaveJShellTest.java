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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import commands.LoadJShell;
import commands.SaveJShell;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class is responsible for testing the SaveJShell class.
 * 
 * @author Marcus
 */
public class SaveJShellTest {

  /** The FileSystem instance used for testing. */
  private FileSystem fileSystem;
  /** The SaveJShell instance to be tested. */
  private SaveJShell saveJShell;

  /**
   * Initializes fileSystem and loadJShell.
   * 
   * @throws Exception An exception SaveJShell can throw.
   */
  @Before
  public void setUp() throws Exception {
    fileSystem = FileSystem.getInstance();
    saveJShell = new SaveJShell(new int[] {1});
  }

  /**
   * Sets the singleton fileSystem object to null, for the purpose of testing
   * again.
   * 
   * @throws Exception An exception SaveJShell can throw.
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
    assertEquals("Invalid Arguments", saveJShell.execute(new String[] {}));
    assertEquals("Invalid Arguments",
        saveJShell.execute(new String[] {"file1 file2"}));
  }

  /**
   * Tests execute with an invalid file name.
   */
  @Test
  public void executeTestInvalidFileName() {
    assertEquals("Invalid File Name",
        saveJShell.execute(new String[] {"file*"}));
  }

  /**
   * Tests execute with a valid file name.
   */
  @Test
  public void executeTestValidInput() {

    // Adds directory, file, and command history to the file system.
    fileSystem.getRootDirectory().addToDir(new Directory("dir1"));
    fileSystem.getRootDirectory().addToDir(new File("file1"));
    fileSystem.getCommandHistory().add("first command");

    LoadJShell loadJShell = new LoadJShell(new int[] {1});

    // Serializes and then deserializes the file system and checks if the
    // correct contents are in the file system.
    assertEquals("Success", saveJShell.execute(new String[] {"file"}));
    fileSystem = loadJShell.execute(new String[] {"file"});
    assertNotNull(fileSystem.getRootDirectory().getSubFile("dir1"));
    assertNotNull(fileSystem.getRootDirectory().getSubFile("file1"));
    assertNull(fileSystem.getRootDirectory().getSubFile("dir2"));
    assertEquals("first command", fileSystem.getCommandHistory().get(0));

  }



}
