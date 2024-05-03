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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import commands.Command;
import commands.MakeDirectory;
import commands.Search;
import commands.Tree;
import driver.JShell;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;
import handling.StandardError;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class is responsible for testing the StandardError class.
 * 
 * @author Marcus
 */
public class StandardErrorTest {

  /** The FileSystem instance used for testing. */
  private FileSystem fileSystem;
  /** The StandardError instance to be tested. */
  private StandardError errorHandler;

  /**
   * Initializes errorHandler and resets the commands on the JShell.
   * 
   * @throws Exception An exception StandardError can throw.
   */
  @Before
  public void setUp() throws Exception {
    fileSystem = FileSystem.getInstance();
    errorHandler = new StandardError();
    JShell.setCommands(new HashMap<String, Command>());
  }

  /**
   * Sets the singleton fileSystem object to null, for the purpose of testing
   * again.
   * 
   * @throws Exception An exception StandardError can throw.
   */
  @After
  public void tearDown() throws Exception {
    Field field = (fileSystem.getClass()).getDeclaredField("shellInstance");
    field.setAccessible(true);
    field.set(null, null);
  }

  /**
   * Tests checkArgumentCount with an invalid argument count.
   */
  @Test
  public void checkArgumentCountTestInvalidCount() {
    int[] validArgumentCounts = {1, 3, 4};
    String[] shellInput = {"a", "b", "c"};
    assertTrue(
        errorHandler.checkArgumentCount(validArgumentCounts, shellInput));
    validArgumentCounts = new int[] {0};
    shellInput = new String[] {"a"};
    assertFalse(
        errorHandler.checkArgumentCount(validArgumentCounts, shellInput));
  }

  /**
   * Tests checkArgumentCount with a negative valid argument count (allowing all
   * arguments with 0 or more elements).
   */
  @Test
  public void checkArgumentCountTestNegativeCount() {
    int[] validArgumentCounts = {-1};
    String[] shellInput = {"a", "b", "c", "d", "e", "f"};
    assertTrue(
        errorHandler.checkArgumentCount(validArgumentCounts, shellInput));
  }

  /**
   * Tests isDuplicateName with a duplicate name.
   */
  @Test
  public void isDuplicateNameTestDuplicateName() {
    Directory dir = new Directory("dir");
    dir.addToDir(new File("file"));
    assertTrue(errorHandler.isDuplicateName(dir, "file"));
  }

  /**
   * Tests isDuplicateName with a non-duplicate name.
   */
  @Test
  public void isDuplicateNameTestNonDuplicateName() {
    Directory dir = new Directory("dir");
    dir.addToDir(new File("file1"));
    assertFalse(errorHandler.isDuplicateName(dir, "file2"));
  }

  /**
   * Tests isValidNumber with a valid positive integer.
   */
  @Test
  public void isValidNumberTestPositiveInteger() {
    String numberAsString = "35";
    assertTrue(errorHandler.isValidNumber(numberAsString));
  }

  /**
   * Tests isValidNumber with a string of non integer characters.
   */
  @Test
  public void isValidNumberTestStringOfNonNumbers() {
    String numberAsString = "Hello World";
    assertFalse(errorHandler.isValidNumber(numberAsString));
  }

  /**
   * Tests isValidNumber with a negative number.
   */
  @Test
  public void isValidNumberTestNegativeNumber() {
    String numberAsString = "-1";
    assertFalse(errorHandler.isValidNumber(numberAsString));
  }

  /**
   * Tests isValidNumber with a positive non-integer.
   */
  @Test
  public void isValidNumberTestPositiveNonInteger() {
    String numberAsString = "3.5";
    assertFalse(errorHandler.isValidNumber(numberAsString));
  }

  /**
   * Tests isValidCommand with a valid command.
   */
  @Test
  public void isValidCommandTestValidCommand() {
    JShell.getCommands().put("tree", new Tree(new int[] {0}));
    JShell.getCommands().put("search", new Search(new int[] {-5}));
    assertTrue(errorHandler.isValidCommand("tree"));
  }

  /**
   * Tests isValidCommand with an invalid command.
   */
  @Test
  public void isValidCommandTestInvalidCommand() {
    JShell.getCommands().put("tree", new Tree(new int[] {0}));
    JShell.getCommands().put("search", new Search(new int[] {-5}));
    assertFalse(errorHandler.isValidCommand("ls"));
  }

  /**
   * Tests isValidString with a valid string.
   */
  @Test
  public void isValidStringTestValidString() {
    String inputString = "\"Hello World\"";
    assertTrue(errorHandler.isValidString(inputString));
  }

  /**
   * Tests isValidString with an invalid string(quotations in middle).
   */
  @Test
  public void isValidStringTestInvalidStirngMiddleQuotations() {
    String inputString = "\"Hello\" World\"";
    assertFalse(errorHandler.isValidString(inputString));
  }

  /**
   * Tests isValidString with an invalid string(no surrounding quotations).
   */
  @Test
  public void isValidStringTestInvalidStirngNoSurroundingQuotations() {
    String inputString = "Hello World";
    assertFalse(errorHandler.isValidString(inputString));
  }

  /**
   * Tests isValidFileName with a valid file name.
   */
  @Test
  public void isValidFileNameTestValidFileName() {
    String inputString = "file1";
    assertTrue(errorHandler.isValidFileName(inputString));
  }

  /**
   * Tests isValidFileName with an invalid file name (single bad character).
   */
  @Test
  public void isValidFileNameTestSingleInvalidCharacter() {
    String inputString = "file1*";
    assertFalse(errorHandler.isValidFileName(inputString));
  }

  /**
   * Tests isValidFileName with an invalid file name (multiple bad characters).
   */
  @Test
  public void isValidFileNameTestMultipleInvalidCharacters() {
    String inputString = ".file1|";
    assertFalse(errorHandler.isValidFileName(inputString));
  }


  /**
   * Tests isValidPath with a valid relative path.
   */
  @Test
  public void isValidPathValidRelativePath() {
    Directory dir1 = new Directory("dir1");
    fileSystem.getRootDirectory().addToDir(dir1);
    dir1.setParentDirectory(fileSystem.getRootDirectory());
    assertTrue(errorHandler.isValidPath("dir1", -1));

    File file1 = new File("file1");
    fileSystem.getRootDirectory().addToDir(file1);
    file1.setParentDirectory(fileSystem.getRootDirectory());
    assertTrue(errorHandler.isValidPath("file1", -2));

    Directory dir2 = new Directory("dir2");
    dir1.addToDir(dir2);
    dir2.setParentDirectory(dir1);
    assertTrue(errorHandler.isValidPath("dir1/dir2", -1));
  }

  /**
   * Tests isValidPath with a valid absolute path.
   */
  @Test
  public void isValidPathValidAbsolutePath() {
    Directory dir1 = new Directory("dir1");
    fileSystem.getRootDirectory().addToDir(dir1);
    dir1.setParentDirectory(fileSystem.getRootDirectory());
    assertTrue(errorHandler.isValidPath("/dir1", -1));

    Directory dir2 = new Directory("dir2");
    dir1.addToDir(dir2);
    dir2.setParentDirectory(dir1);
    assertTrue(errorHandler.isValidPath("/dir1/dir2", -1));

    assertTrue(errorHandler.isValidPath("/", -1));
  }

  /**
   * Tests isValidPath with an invalid relative path.
   */
  @Test
  public void isValidPathInvalidRelativePath() {
    Directory dir1 = new Directory("dir1");
    fileSystem.getRootDirectory().addToDir(dir1);
    dir1.setParentDirectory(fileSystem.getRootDirectory());
    assertFalse(errorHandler.isValidPath("dir2", -1));
    assertFalse(errorHandler.isValidPath("dir1/dir2", -1));
    assertFalse(errorHandler.isValidPath("dir3/file1", -2));
  }

  /**
   * Tests isValidPath with an invalid absolute path.
   */
  @Test
  public void isValidPathInvalidAbsolutePath() {
    Directory dir1 = new Directory("dir1");
    fileSystem.getRootDirectory().addToDir(dir1);
    dir1.setParentDirectory(fileSystem.getRootDirectory());
    assertFalse(errorHandler.isValidPath("/dir2", -1));
    assertFalse(errorHandler.isValidPath("/dir3/file1", -2));
    assertFalse(errorHandler.isValidPath("//", -1));
  }

  /**
   * Tests isValidFileObj with a valid file object (file).
   */
  @Test
  public void isValidFileObjTestValidFile() {
    Directory dir = new Directory("dir");
    dir.addToDir(new File("file"));
    assertTrue(errorHandler.isValidFileObj(dir, "file"));
  }

  /**
   * Tests isValidFileObj with a valid file object (directory).
   */
  @Test
  public void isValidFileObjTestValidDirectory() {
    Directory dir = new Directory("dir");
    dir.addToDir(new Directory("dir1"));
    assertTrue(errorHandler.isValidFileObj(dir, "dir1"));
  }

  /**
   * Tests isValidFileObj with an invalid file object.
   */
  @Test
  public void isValidFileObjTestInvalidFileObj() {
    Directory dir = new Directory("dir");
    dir.addToDir(new Directory("dir1"));
    dir.addToDir(new File("file1"));
    assertFalse(errorHandler.isValidFileObj(dir, "dir2"));
  }

  /**
   * Tests isValidFile with a valid file.
   */
  @Test
  public void isValidFileTestValidFile() {
    Directory dir = new Directory("dir");
    dir.addToDir(new File("file1"));
    assertTrue(errorHandler.isValidFile(dir, "file1"));
  }

  /**
   * Tests isValidFile with an invalid file object (directory).
   */
  @Test
  public void isValidFileTest2() {
    Directory dir = new Directory("dir");
    dir.addToDir(new Directory("dir1"));
    assertFalse(errorHandler.isValidFile(dir, "dir1"));
  }

  /**
   * Tests isValidFile with an invalid file.
   */
  @Test
  public void isValidFileTest3() {
    Directory dir = new Directory("dir");
    dir.addToDir(new File("file1"));
    dir.addToDir(new File("file2"));
    assertFalse(errorHandler.isValidFile(dir, "file3"));
  }

  /**
   * Tests isValidSearchArgs with arguments that are missing -type [f|d] -name
   * "expression" in the right order.
   */
  @Test
  public void isValidSearchArgsWithInvalidArgs() {

    // Checks if there are at least 5 arguments in the string
    String[] args = {"-type", "d", "-name", "\"expression\""};
    assertFalse(errorHandler.isValidSearchArgs(args));

    // Checks if -type is in the right location
    args = new String[] {"dir", "type", "d", "-name", "\"expression\""};
    assertFalse(errorHandler.isValidSearchArgs(args));

    // Checks if f or d is in the right location
    args = new String[] {"dir", "-type", "!", "-name", "\"expression\""};
    assertFalse(errorHandler.isValidSearchArgs(args));

    // Checks if -name is in the right location
    args = new String[] {"dir", "-type", "d", "name", "\"expression\""};
    assertFalse(errorHandler.isValidSearchArgs(args));

    // Checks if expression is in quotations and in the right location
    args = new String[] {"dir", "-type", "d", "-name", "expression"};
    assertFalse(errorHandler.isValidSearchArgs(args));
    args = new String[] {"dir", "-type", "d", "-name", "expression\""};
    assertFalse(errorHandler.isValidSearchArgs(args));
    args = new String[] {"dir", "-type", "d", "-name", "\"expression"};
    assertFalse(errorHandler.isValidSearchArgs(args));
  }

  /**
   * Tests isValidSearchArgs with a valid argument.
   */
  @Test
  public void isValidSearchArgsWithValidArg() {
    String[] args =
        {"no_missing_arguments", "-type", "d", "-name", "\"expression" + "\""};
    assertTrue(errorHandler.isValidSearchArgs(args));
  }

  /**
   * Tests isValidMoveTest with a valid argument.
   */
  @Test
  public void isValidMoveTest() {
    MakeDirectory mkdirObj = new MakeDirectory(new int[] {-2});
    mkdirObj.execute(new String[] {"dir1 dir2"});
    Directory dir1 =
        (Directory) fileSystem.getRootDirectory().getSubFile("dir1");
    Directory dir2 =
        (Directory) fileSystem.getRootDirectory().getSubFile("dir2");
    assertTrue(errorHandler.isValidMove(dir1, dir2));
  }

  /**
   * Tests isValidMoveTest with a invalid argument.
   */
  @Test
  public void isValidMoveTestInvalid() {
    MakeDirectory mkdirObj = new MakeDirectory(new int[] {-2});
    mkdirObj.execute(new String[] {"dir1 dir2 dir1/dirInsidedir1"});
    Directory dir1 =
        (Directory) fileSystem.getRootDirectory().getSubFile("dir1");
    Directory dir2 =
        (Directory) fileSystem.getRootDirectory().getSubFile("dir2");
    Directory dirInsidedir1 = (Directory) dir1.getSubFile("dirInsidedir1");

    // test moving a directory to its child directory
    assertFalse(errorHandler.isValidMove(dir1, dirInsidedir1));
    // test moving a root directory
    assertFalse(errorHandler.isValidMove(fileSystem.getRootDirectory(), dir2));
    // test moving to the same directory
    assertFalse(errorHandler.isValidMove(dirInsidedir1, dir1));
  }
}
