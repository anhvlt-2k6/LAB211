package v03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * V03 - File Processor Backend
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-29
 */
public class FileProcessor {
    
    /**
     * Check Input Path, where the path is dir or file or neither
     * @param path as String for the path
     * @return String as "dir" for directory, "file" for files, or "" for else
     */
    public String checkInputPath(String path) {
        // Initialize the return type. It can be empty, "dir", or "file"
        String typeofInput = "";
        
        // File intialize
        File file = new File(path);
        
        // Check if path exist
        if (file.exists()) {
            // Check if path is dir. if so set typeofInput to "dir", else for "file"
            if (file.isDirectory()) {
                typeofInput = "dir";
            } else if (file.isFile()) {
                typeofInput = "file";
            }
        }
        
        // Return the value back
        return (typeofInput);
    }
    
    /**
     * Get Java files
     * @param path as String for the path
     * @return an array of String that are the java files
     */
    public ArrayList<String> getAllFileNameJavaInDirectory(String path) {
        // Initialize the return type.
        ArrayList<String> fileNames = new ArrayList<>();
        
        // File intialize
        File directory = new File(path);
        
        // List files from directory
        File[] files = directory.listFiles();
        
        // If the files is not null, proceed for add files that ends with ".java"
        if (files != null) {
            // For each f in files, get the name, compare the suffix, and if
            //  it matches for ".java", add it into the return value
            for (File f : files) {
                String fn = f.getName();
                if (fn.toLowerCase().endsWith(".java")) {
                    fileNames.add(fn);
                }
            }
        }
        
        // Return the value back
        return (fileNames);
    }
    
    /**
     * Get files largers than input
     * @param path as String for the path
     * @param size as minimum size of file
     * @return File that matches the condition of size
     * @throws Exception in case of path is not directory
     */
    public ArrayList<File> getFileWithSizeGreaterThanInput(String path, int size) throws Exception {
        // Initialize the return type.
        ArrayList<File> fileNames = new ArrayList<>();
        
        // File intialize
        File dir = new File(path);
        
        // If the dir is an directory, list files from that
        if (dir.isDirectory()) {
            // List all files from that directory
            File[] files = dir.listFiles();
            
            // If the array files is not null 
            if (files != null) {
                // For each f in files, get the size, and if it matches the condition
                //  add the file into the return value
                for (File f : files) {
                    if (f.length() > ((long)(size) * 1024L)) {
                        fileNames.add(f);
                    }
                }
            }
        }
        else {
            // Throw exception in case the path is not a dir
            throw new Exception("Path is not a directory");
        }
        
        // Return the value back
        return (fileNames);
    }
    
    /**
     * Append content into the file
     * @param path as String for the path
     * @param contentInput as the string user wants to append
     * @return boolean if the append is success
     * @throws Exception in case unable to write file
     */
    public boolean appendContentToFile(String path, String contentInput) throws Exception {
        // Initialize the return value and cease for worst case
        boolean isAppendSuccess = false;
        
        // Initialize the file pointer
        File pt = new File(path);
        
        // If the file can be write, proceed next
        if (pt.canWrite()) {
            // try-catch with inner value
            try (FileWriter fw = new FileWriter(path, true)) {
                // append with the user input string
                fw.append(contentInput);
                // set return value to true
                isAppendSuccess = true;  
            }
            catch (IOException ioex) {
                // throw exception in case unable to write file
                throw new Exception("Unable to write file.");
            }
        }
        
        // return the value
        return (isAppendSuccess);
    }
    
    /**
     * Count characters in a file
     * @param path as String for the path
     * @return numbers of characters in a file
     * @throws Exception in case unable to read the file
     */
    public int countCharacter(String path) throws Exception {
        // Initialize the return value
        int words = 0;
        
        // Initialize the file pointer
        File fw = new File(path);
        
        // Check if the file can be read
        if (!fw.canRead() || !fw.isFile() || !fw.exists()) {
            throw new Exception("Path cannot be read");
        }
        
        // Loop for lines in file
        try (Scanner sc = new Scanner(fw)) {
            // Loop for lines in file
            while (sc.hasNext()) {
                sc.next();
                // For each lines, count characters
                words += 1;
            }
        }
        
        // Return number of characters
        return (words);
    }
}
