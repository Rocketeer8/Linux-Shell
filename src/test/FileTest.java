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

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import filesystem.File;
import filesystem.Directory;
import filesystem.FileSystem;

public class FileTest {
  
  FileSystem fs;
  @Before
  public void setUp() throws Exception {
       fs = FileSystem.getInstance();
  }
  
  @After
  public void tearDown() throws Exception {
     // reset FileSystem instance to empty after each test
    Field field = (fs.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null); //setting the shellInstance parameter to null
  }

  @Test
  public void executeTestCreateFile() {
    Directory root = fs.getRootDirectory();
    File newFile = new File("file1");
    root.addToDir(newFile);
    newFile.setParentDirectory(root);
    assertEquals(root.getSubFile("file1"), newFile);
  }
  
  @Test
  public void executeTestContentOfFile() {
    Directory root = fs.getRootDirectory();
    File newFile = new File("file1", "Hello World!");
    root.addToDir(newFile);
    newFile.setParentDirectory(root);
    assertEquals(root.getSubFile("file1").toString(), "Hello World!");
  }

}
