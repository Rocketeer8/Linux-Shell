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

package filesystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Stack;


/**
 * This is the class that represent a directory(doesn't include directory). This
 * class inherit from FileObj class.
 * 
 * @author Danny, Brandon, Marcus
 */
public class Directory extends FileObj implements Iterable<FileObj> {

  private static final long serialVersionUID = 1L;

  /** Using LinkedHashMap to store content of a directory for easy access. */
  private LinkedHashMap<String, FileObj> contentInHashMap;

  /**
   * Constructor of Directory, set up the directory's name, parent, and link to
   * its subfiles.
   * 
   * @param name Name of the directory.
   */
  public Directory(String name) {
    // name here refer to local variable name
    super(name);
    this.contentInHashMap = new LinkedHashMap<String, FileObj>();
  }

  /**
   * Get the specified sub file from this directory.
   * 
   * @param fileName Name of the file to get.
   * @return The specified file
   */
  public FileObj getSubFile(String fileName) {
    // if no match, return null
    return this.contentInHashMap.get(fileName);
  }

  /**
   * Add a file to this directory.
   * 
   * @param file The file to add to this directory
   */
  public void addToDir(FileObj file) {
    this.contentInHashMap.put(file.getName(), file);
  }

  /**
   * remove a sub file to this directory.
   * 
   * @param file The sub file to be remove from this directory
   */
  public void removeFromDir(FileObj file) {
    this.contentInHashMap.remove(file.getName());
  }


  /**
   * Returns the contents of the directory as an array list of file objects.
   * 
   * @return The contents of the directory as an array list of file objects.
   */
  public ArrayList<FileObj> getSubFiles() {
    return new ArrayList<FileObj>(contentInHashMap.values());
  }

  /**
   * This method gets the list of names of its subfiles in the directory.
   * 
   * @return The list of names of this directory's subfiles.
   */
  public ArrayList<String> subFileNameList() {
    // this method returns a list of names of subfiles in the directory
    // updated this method to work with HashMaps instead of array list
    ArrayList<String> contentList = new ArrayList<String>();
    for (String i : contentInHashMap.keySet()) {
      contentList.add(i);
    }
    return contentList;
  }

  /**
   * Returns the iterator of the directory.
   * 
   * @return The directory's iterator.
   */
  @Override
  public Iterator<FileObj> iterator() {
    return new DirectoryIterator(this);
  }

  /**
   * This class is responsible for making directory an iterator by traversing
   * through the contents of the directory.
   * 
   * @author Marcus, Danny
   */
  private class DirectoryIterator implements Iterator<FileObj> {
    /** The stack to hold the directories to be traversed. */
    Stack<FileObj> stack;

    /**
     * Creates a new DirectoryIterator object and initializes the stack and adds
     * all the subfiles of dir to the stack.
     * 
     * @param dir The directory to be iterated through.
     */
    public DirectoryIterator(Directory dir) {
      stack = new Stack<FileObj>();
      addSubFilesToStack(dir);
    }

    /**
     * Returns true if the iterator has not traversed through the entire
     * directory, and false otherwise.
     * 
     * @return A boolean depending on if the directory is fully traversed.
     */
    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    /**
     * Returns the next file object contained in the tree of the directory's
     * sub-directories.
     * 
     * @return The next file object in directory's sub-directories.
     */
    @Override
    public FileObj next() {
      if (hasNext()) {
        FileObj curFile = stack.pop();

        // If the next file object is a directory, add their sub files to the
        // stack, return the file object either way
        if (curFile instanceof Directory) {
          addSubFilesToStack((Directory) curFile);
        }
        return curFile;
      }
      return null;
    }

    /**
     * Modifies stk by adding the sub-files of dir to the stk.
     * 
     * @param dir The directory whose sub-files will be added to stk.
     */
    private void addSubFilesToStack(Directory dir) {
      if (dir != null) {
        int size = dir.getSubFiles().size();
        // Traverse through file objects in reverse other because of stack
        // mechanism
        for (int i = size - 1; i >= 0; i--) {
          stack.add(dir.getSubFiles().get(i));
        }
      }
    }

  }



}
