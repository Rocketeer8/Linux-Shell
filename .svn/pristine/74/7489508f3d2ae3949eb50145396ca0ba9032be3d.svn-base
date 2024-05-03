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
import commands.RemoveDirectory;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RemoveDirectoryTest {

  RemoveDirectory rmObj;
  ListContent lsObj;
  FileSystem fs;

  @Before
  public void setUp() throws Exception {
    rmObj = new RemoveDirectory(new int[] {1});
    lsObj = new ListContent(new int[] {-1});
    fs = FileSystem.getInstance();
  }

  @After
  public void tearDown() throws Exception {
    // reset FileSystem instance to empty after each test
    Field field = (fs.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null); // setting the shellInstance parameter to null
  }

  @Test
  public void executeTestRemoveSingleDir() {
    // create two directories in the root
    Directory root = fs.getRootDirectory();
    Directory newDir1 = new Directory("dir1");
    root.addToDir(newDir1);
    newDir1.setParentDirectory(root);
    Directory newDir2 = new Directory("dir2");
    root.addToDir(newDir2);
    newDir2.setParentDirectory(root);
    // move dir2 inside dir1
    rmObj.execute(new String[] {"dir2"});
    String lsOutput = lsObj.execute(new String[] {});
    assertEquals(lsOutput, "dir1\n");
  }

  @Test
  public void executeTestRemoveDirWithSubfiles() {
    // create two directories in the root
    Directory root = fs.getRootDirectory();
    Directory newDir1 = new Directory("dirToBeRemoved");
    root.addToDir(newDir1);
    newDir1.setParentDirectory(root);
    Directory newDir2 = new Directory("dirToStay");
    root.addToDir(newDir2);
    newDir2.setParentDirectory(root);
    // create a subfile and a sub directory inside dirToBeRemoved
    Directory dirToRemove = new Directory("removeDir");
    newDir1.addToDir(dirToRemove);
    dirToRemove.setParentDirectory(newDir1);
    File fileToRemove = new File("removeFile");
    newDir1.addToDir(fileToRemove);
    fileToRemove.setParentDirectory(newDir1);
    // remove dirToBeRemoved and all it's subfiles
    rmObj.execute(new String[] {"dirToBeRemoved"});
    String lsOutput = lsObj.execute(new String[] {});
    // check if "dirToStay" is the only remain file in the root
    assertEquals(lsOutput, "dirToStay\n");
  }
}
