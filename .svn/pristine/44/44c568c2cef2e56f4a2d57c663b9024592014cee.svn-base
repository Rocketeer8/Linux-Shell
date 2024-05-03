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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import commands.MakeDirectory;
import filesystem.Directory;
import filesystem.FileSystem;
import handling.PathHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PathHandlerTest {
  PathHandler phObj;
  MakeDirectory mkdirObj;
  FileSystem fs;
  
  /**
   * Initialize related field for testing PathHandler.
   * @throws Exception  An exception PathHandler can throw. 
   */
  @Before
  public void setUp() throws Exception {
    mkdirObj = new MakeDirectory(new int[] {-2});
    fs = FileSystem.getInstance();
    phObj = new PathHandler();
  }
  
  /**
   * Clear fileSystem after testing.
   * @throws Exception  An exception PathHandler can throw.
   */
  @After
  public void tearDown() throws Exception {
    // reset FileSystem instance to empty after each test
    Field field = (fs.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null); // setting the shellInstance parameter to null
  }
  
  /**
   * test if traverse() can traverse through a path 
   * and return the correct file.
   */
  @Test
  public void traverseTest() {
    // see if traverse() does traverse path to the correct directory
    mkdirObj.execute(new String[] {"dir1 dir1/dirInsideDir1"});
    Directory targetDir = phObj.traverse("dir1/dirInsideDir1", -1);

    Directory dir1 = (Directory) fs.getRootDirectory().getSubFile("dir1");
    Directory dirInsideDir1 = (Directory) dir1.getSubFile("dirInsideDir1");

    assertEquals(targetDir, dirInsideDir1);
  }
  
  /**
   * test if traverse() for root path.
   */
  @Test
  public void traverseTestRoot() {
    // see if traverse() works for root directory
    Directory targetDir = phObj.traverse("/", -1);

    assertEquals(targetDir, fs.getRootDirectory());
  }
  
  /**
   * given a file, test if getPath() can return the absolute path of that file.
   */
  @Test
  public void getPathTest() {
    // make directories first then get path and see if it's correct
    ArrayList<Directory> dirList =
        mkdirObj.execute(new String[] {"dir1 dir1/dirInsideDir1"});
    // get one of the directory and get path on it
    Directory dirInsideDir1 = dirList.get(1);
    String path = phObj.getPath(dirInsideDir1);
    assertEquals(path, "/dir1/dirInsideDir1");
  }
  
  /**
   * given a root, test if getPath() can return the path for root.
   */
  @Test
  public void getPathTestRoot() {
    // test if get path works with root
    String path = phObj.getPath(fs.getRootDirectory());
    assertEquals(path, "/");
  }
  
  /**
   * given a path, test getName() which return the name of directory at
   * specific index.
   */
  @Test
  public void getNameTest() {
    String dirName = phObj.getName("dir1/dir2/dir3/targetDir", -1);
    assertEquals(dirName, "targetDir");
  }
  
  /**
   * test getName() with index not the last one.
   */
  @Test
  public void getNameTestIndexInBetween() {
    String dirName = phObj.getName("dir1/targetDir/dir3/dir4", -3);
    assertEquals(dirName, "targetDir");
  }
  
  /**
   * test analyzePat() on a path and see if it returns the correct String
   * Array representation.
   */
  @Test
  public void analyzePathTest() {
    String[] arrayOfDirs = phObj.analyzePath("dir1/dir2/dir3/dir4");
    String[] expectedArrayDirs = {"dir1", "dir2", "dir3", "dir4"};
    assertArrayEquals(arrayOfDirs, expectedArrayDirs);
  }
  
  /**
   * test if getLevel() return the depth of a given path.
   */
  @Test
  public void getLevelTest() {
    int level = phObj.getLevel("dir1/dir2/dir3/dir4");
    assertEquals(level, 4);
  }
  
  /**
   * test if getLevel() return the depth of a given absolute path.
   */
  @Test
  public void getLevelTestWithRoot() {
    int level = phObj.getLevel("/dir1/dir2");
    assertEquals(level, 3);
  }
}
