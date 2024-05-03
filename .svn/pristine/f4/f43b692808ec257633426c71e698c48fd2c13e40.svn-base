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
import commands.Move;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MoveTest {

  Move mvObj;
  ListContent lsObj;
  FileSystem fs;

  @Before
  public void setUp() throws Exception {
    mvObj = new Move(new int[] {2});
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
  public void executeTestMoveSingleDir() {
    // create two directories in the root
    Directory root = fs.getRootDirectory();
    Directory newDir1 = new Directory("dir1");
    root.addToDir(newDir1);
    newDir1.setParentDirectory(root);
    Directory newDir2 = new Directory("dir2");
    root.addToDir(newDir2);
    newDir2.setParentDirectory(root);
    // move dir2 inside dir1
    mvObj.execute(new String[] {"dir2 /dir1"});
    String lsOutput1 = lsObj.execute(new String[] {});
    assertEquals(lsOutput1, "dir1\n");
    String lsOutput2 = lsObj.execute(new String[] {"/dir1"});
    assertEquals(lsOutput2, "dir2\n");
  }

  @Test
  public void executeTestMoveFile() {
    // create a subfile in the root
    Directory root = fs.getRootDirectory();
    File newFile = new File("moveFile", "Hello World!");
    root.addToDir(newFile);
    newFile.setParentDirectory(root);
    // create a directory in the root
    Directory newDir = new Directory("testDir");
    root.addToDir(newDir);
    newDir.setParentDirectory(root);
    // move moveFile inside testDir
    File moveFile = (File) mvObj.execute(new String[] {"moveFile /testDir"});
    String lsOutput1 = lsObj.execute(new String[] {});
    // This checks that the original file is removed after moved
    assertEquals(lsOutput1, "testDir\n");
    String lsOutput2 = lsObj.execute(new String[] {"/testDir"});
    // This checks if a new copied file is now located in the destination
    assertEquals(lsOutput2, "moveFile\n");
    String contentOfOriginalFile = newFile.toString();
    String contentOfmoveFile = moveFile.toString();
    // This checks if the original file and moved file store the same content
    assertEquals(contentOfOriginalFile, contentOfmoveFile);
  }

  @Test
  public void executeTestMoveDirWithSubfiles() {
    // create two directories in the root
    Directory root = fs.getRootDirectory();
    Directory newDir1 = new Directory("moveFromDir");
    root.addToDir(newDir1);
    newDir1.setParentDirectory(root);
    Directory newDir2 = new Directory("moveToDir");
    root.addToDir(newDir2);
    newDir2.setParentDirectory(root);
    // create a subfile and a sub directory inside moveFromDir
    Directory dirToMove = new Directory("moveDir");
    newDir1.addToDir(dirToMove);
    dirToMove.setParentDirectory(newDir1);
    File fileToMove = new File("moveFile");
    newDir1.addToDir(fileToMove);
    fileToMove.setParentDirectory(newDir1);
    String lsOutput1 = lsObj.execute(new String[] {"moveFromDir"});
    // copy moveFromDir inside moveToDir
    mvObj.execute(new String[] {"moveFromDir moveToDir"});
    String lsOutput2 = lsObj.execute(new String[] {"/moveToDir/moveFromDir"});
    // check if moveFromDir's content is the same as before moved
    assertEquals(lsOutput1, lsOutput2);
  }
}
