/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v03;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * V03 - File Processor Front End
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-29
 */
public class UserInterface {
    
    // SCanner for user input and file processor backend
    private final Scanner sc;
    private final FileProcessor fp;
    
    /**
     * Constructor of the user user interface
     */
    public UserInterface() {
        sc = new Scanner(System.in);
        fp = new FileProcessor();
    }
    
    /**
     * Count Characters of File User Interface - Choice 5
     */
    private void countCharacterofFiles() {
        try {
            // initialize the character, in case unable to get the value
            int characters = 0;
            
            // Ask user to enter the right path of file. If don't ask until enter the right path
            String path = "";
            while (!fp.checkInputPath(path).equals("file")) {
                System.out.print("Input a path: ");
                String pathUI = sc.nextLine().trim(); // trim for remove spaces
                
                // Check for new user input. 
                if (!fp.checkInputPath(pathUI).equals("file")) {
                    // Notify user
                    System.out.println("Path is not a file");
                } else {
                    // If valid, assign the new value into the path
                    path = pathUI;
                }
            }
            
            // Call the backend for the character count
            characters = fp.countCharacter(path);
            
            // Print that value
            System.out.println("Total: " + characters);
        } catch (Exception e) {
            // Notify user
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Append Content to File
     */
    private void appendContentToFile() {
        try {
            
            System.out.println(
                    "------ Write more content to file ------"
            );
            
            // Ask user to enter content
            System.out.print("Enter content:");
            String content = sc.nextLine(); // no trim, respect the user input
            
            // Ask user to enter the right path of file. If don't ask until enter the right path
            System.out.print("Input a path: ");
            String pathUI = sc.nextLine().trim(); // trim for remove spaces
            
            // Call the file processor backend for the file of appending and its content
            boolean isAppendSuccess = fp.appendContentToFile(pathUI, content);
            
            // if the append is success, notify user
            if (isAppendSuccess) {
                System.out.println("Write Done");
            } else {
                // Notfiy User
                System.out.println("Write is incompleted");
            }
        } catch (Exception e) {
            // Notfiy User
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Get the file larger than user input size
     */
    private void getFileLargerThanSize() {
        
        try {
            System.out.println(
                    "------ Get file with size greater than input ------"
            );
            
            // Ask user to enter the size of file (in minimum)
            String sizeStr = "";
            while (!sizeStr.matches("[0-9]{1,255}")) {                
                System.out.print("Enter size (Integer):");
                String userInput = sc.nextLine().trim(); // remove spaces
                
                // Verify the new input
                if (!userInput.matches("[0-9]{1,255}")) {
                    System.out.println("Size is digit!");
                } else {
                    sizeStr = userInput;
                }
            }
            
            // Ask user to enter the right path of dir. If don't ask until enter the right path
            String path = "";
            while (!fp.checkInputPath(path).equals("dir")) {
                System.out.print("Input a path: ");
                String pathUI = sc.nextLine().trim();
                
                // Check for new user input. 
                if (!fp.checkInputPath(pathUI).equals("dir")) {
                    // Notify user
                    System.out.println("Path is not a dir");
                } else {
                    // If valid, assign the new value into the path
                    path = pathUI;
                }
            }
            
            // Call the file processor backend for the File
            ArrayList<File> files = fp.getFileWithSizeGreaterThanInput(path, Integer.parseInt(sizeStr));
            
            // Display for matches
            System.out.println(String.format("Result %d files", files.size()));
            
            // Loop to get the file names
            if (!files.isEmpty()) {
                for (File f : files) {
                    // Print out the file name
                    System.out.println(f.getName());
                }
            }
        } catch (Exception e) {
            // Notify user
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Get Java files
     */
    private void getJavaFiles() {
        try {
            System.out.println(
                    "------ Get file name with type Java ------"
            );
            
            // Ask user to enter the right path of dir. If don't ask until enter the right path
            String path = "";
            while (!fp.checkInputPath(path).equals("dir")) {
                System.out.print("Input a path: ");
                String pathUI = sc.nextLine().trim();
                
                // Check for new user input. 
                if (!fp.checkInputPath(pathUI).equals("dir")) {
                    // Notify user
                    System.out.println("Path is not a dir");
                } else {
                    // If valid, assign the new value into the path
                    path = pathUI;
                }
            }
            
            // Call the file processor backend for the File
            ArrayList<String> pathF = fp.getAllFileNameJavaInDirectory(path);
            
            // Display for matches
            System.out.println(String.format("Result %d files", pathF.size()));
            
            // Loop to get the file names
            if (!pathF.isEmpty()) {
                for (String s : pathF) {
                    // Print for file names
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            // Noify users
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Check if the path is dir or file or none
     */
    private void checkPath() {
        try {
            System.out.print(
                    "------------ Check Path ------------\n"
                    + "Enter path: "
            );
            
            // Ask user to enter the path
            String pathType = fp.checkInputPath(sc.nextLine().trim()); // trim to remove spaces
            
            // if the pathType if "dir" its directory, or "file" as a file, "" is not a valid path
            if (pathType.equals("dir")) {
                System.out.println("Path to Directory");
            } else if (pathType.equals("file")) {
                System.out.println("Path to File");
            } else {
                System.out.println("Path does not exist");
            }
        } catch (Exception e) {
            // Notify user
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Display User Interface
     */
    public void displayUserInterface() {
        while(true) {
            try {
                System.out.print(
                        "============ File Processing ============\n"
                        + "1. Check Path\n"
                        + "2. Get file name with type java\n"
                        + "3. Get file with greater than input\n"
                        + "4. Write more content to file\n"
                        + "5. Read file and count words\n"
                        + "6. Exit\n"
                        + "Please choice one option:"
                );
                
                // ASk user for choice
                int choice = Integer.parseInt(sc.nextLine().trim());
                
                // FOr each choice, call the correct function for user
                switch (choice) {
                    case 1:
                        this.checkPath();
                        break;
                    case 2:
                        this.getJavaFiles();
                        break;
                    case 3:
                        this.getFileLargerThanSize();
                        break;
                    case 4:
                        this.appendContentToFile();
                        break;
                    case 5:
                        this.countCharacterofFiles();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        // Other choice are invalid
                        System.out.println("Invalid choice");
                        break;
                }
            }
            catch (NumberFormatException e) {
                // Notify users
                System.out.println("Error: " + e.getLocalizedMessage());
            }
        }
    }
}
