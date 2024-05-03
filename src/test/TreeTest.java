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

import commands.Tree;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class is responsible for testing the Tree class.
 * 
 * @author Marcus
 */
public class TreeTest {

  /** The FileSystem instance used for testing. */
  private FileSystem fileSystem;
  /** The Tree instance to be tested. */
  private Tree tree;

  /**
   * Initializes fileSystem and tree.
   * 
   * @throws Exception An exception Tree can throw.
   */
  @Before
  public void setUp() throws Exception {
    fileSystem = FileSystem.getInstance();
    tree = new Tree(new int[] {0});
  }

  /**
   * Sets the singleton fileSystem object to null, for the purpose of testing
   * again.
   * 
   * @throws Exception An exception Tree can throw.
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
    assertEquals("Invalid arguments", tree.execute(new String[] {"dir1"}));
    assertEquals("Invalid arguments",
        tree.execute(new String[] {"dir1", "abc"}));
  }

  /**
   * Tests execute with an empty file system (only root).
   */
  @Test
  public void executeTestEmptyFileSystem() {
    String output = tree.execute(new String[] {});
    assertEquals("/\n", output);
  }

  /**
   * Tests tree with multiple file objects in the root directory.
   */
  @Test
  public void executeTestFileObjsInRoot() {
    Directory dir1 = new Directory("dir1");
    File file1 = new File("file1");

    fileSystem.getRootDirectory().addToDir(dir1);
    dir1.setParentDirectory(fileSystem.getRootDirectory());
    fileSystem.getRootDirectory().addToDir(file1);
    file1.setParentDirectory(fileSystem.getRootDirectory());

    Directory dir2 = new Directory("dir2");

    fileSystem.getRootDirectory().addToDir(dir2);;
    dir2.setParentDirectory(fileSystem.getRootDirectory());
    String output = tree.execute(new String[] {});

    assertEquals("/\n   dir1\n   file1\n   dir2\n", output);
  }

  /**
   * Tests execute with directories in the root, and with file objects nested in
   * those directories.
   */
  @Test
  public void executeTestFileObjsInNestedDirectories() {
    Directory dir1 = new Directory("dir1");
    Directory dir2 = new Directory("dir2");

    fileSystem.getRootDirectory().addToDir(dir1);
    dir1.setParentDirectory(fileSystem.getRootDirectory());
    fileSystem.getRootDirectory().addToDir(dir2);;
    dir2.setParentDirectory(fileSystem.getRootDirectory());

    File file1 = new File("file1");
    dir1.addToDir(file1);
    file1.setParentDirectory(dir1);
    Directory dir3 = new Directory("dir3");
    dir1.addToDir(dir3);
    dir3.setParentDirectory(dir1);
    Directory dir4 = new Directory("dir4");
    dir2.addToDir(dir4);
    dir4.setParentDirectory(dir2);

    String output = tree.execute(new String[] {});
    assertEquals("/\n   dir1\n      file1\n      dir3\n   dir2\n      dir4\n",
        output);
  }

}
