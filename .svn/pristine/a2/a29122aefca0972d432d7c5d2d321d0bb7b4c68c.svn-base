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

import commands.Copy;
import commands.ListContent;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CopyTest {

  Copy cpObj;
  ListContent lsObj;
  FileSystem fs;

  @Before
  public void setUp() throws Exception {
    cpObj = new Copy(new int[] {2});
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
  public void executeTestCopySingleDir() {
    // create two directories in the root
    Directory root = fs.getRootDirectory();
    Directory newDir1 = new Directory("dir1");
    root.addToDir(newDir1);
    newDir1.setParentDirectory(root);
    Directory newDir2 = new Directory("dir2");
    root.addToDir(newDir2);
    newDir2.setParentDirectory(root);
    // copy dir2 inside dir1
    cpObj.execute(new String[] {"dir2 /dir1"});
    String lsOutput1 = lsObj.execute(new String[] {});
    assertEquals(lsOutput1, "dir1\ndir2\n");
    String lsOutput2 = lsObj.execute(new String[] {"/dir1"});
    assertEquals(lsOutput2, "dir2\n");
  }

  @Test
  public void executeTestCopyFile() {
    // create a subfile in the root
    Directory root = fs.getRootDirectory();
    File newFile = new File("copyFile", "Hello World!");
    root.addToDir(newFile);
    newFile.setParentDirectory(root);
    // create a directory in the root
    Directory newDir = new Directory("testDir");
    root.addToDir(newDir);
    newDir.setParentDirectory(root);
    // copy copyFile inside testDir
    File copyFile = (File) cpObj.execute(new String[] {"copyFile /testDir"});
    String lsOutput1 = lsObj.execute(new String[] {});
    // This checks that the original file is not removed after copied
    assertEquals(lsOutput1, "copyFile\ntestDir\n");
    String lsOutput2 = lsObj.execute(new String[] {"/testDir"});
    // This checks if a new copied file is created in the destination
    assertEquals(lsOutput2, "copyFile\n");
    String contentOfOriginalFile = newFile.toString();
    String contentOfCopyFile = copyFile.toString();
    // This checks if the original file and copied file store the same content
    assertEquals(contentOfOriginalFile, contentOfCopyFile);
  }

  @Test
  public void executeTestCopyDirWithSubfiles() {
    // create two directories in the root
    Directory root = fs.getRootDirectory();
    Directory newDir1 = new Directory("copyFromDir");
    root.addToDir(newDir1);
    newDir1.setParentDirectory(root);
    Directory newDir2 = new Directory("copyToDir");
    root.addToDir(newDir2);
    newDir2.setParentDirectory(root);
    // create a subfile and a sub directory inside copyFromDir
    Directory dirToCopy = new Directory("copyDir");
    newDir1.addToDir(dirToCopy);
    dirToCopy.setParentDirectory(newDir1);
    File fileToCopy = new File("copyFile");
    newDir1.addToDir(fileToCopy);
    fileToCopy.setParentDirectory(newDir1);
    // copy copyFromDir inside copyToDir
    cpObj.execute(new String[] {"copyFromDir copyToDir"});
    String lsOutput1 = lsObj.execute(new String[] {"copyFromDir"});
    String lsOutput2 = lsObj.execute(new String[] {"/copyToDir/copyFromDir"});
    // check if copyFromDir and it's replica has the same content
    assertEquals(lsOutput1, lsOutput2);
  }
}
