package short04.UserDb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Short 04 - UserList
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-18
 */
@SuppressWarnings("serial")
public class UserList extends ArrayList<User> {
    
    // Database file name
    private final String fileName = "userdb.txt";
    
    // File reader and writer
    private Scanner userDbReader;
    private FileWriter userDbWriter;
    
    /**
     * Constructor of the list
     */
    public UserList() {
        this.readFromDb();
    }
    
    ///////////////////////////////////////////////////////
    ////// Non-selection-based methods
    ///////////////////////////////////////////////////////

    /**
     * Write content from the ArrayList into the database (see the variable fileName)
     */
    
    private void writeToDb() {
        // Pseudo line (First Line) as the header
        String outStr = "#ID,Account,Password,Name,Phone,Email,Address,DOB";

        try {
            // Initialize new database writer
            userDbWriter = new FileWriter(fileName);
            
            // Write into structure. See the Pseudo line above.
            outStr = this.stream().map((u) -> String.format(
                    "\n%d,%s,%s,%s,%s,%s,%s,%s",
                    u.getId(),
                    u.getUserName(),
                    u.getPassword(),
                    u.getName(),
                    u.getPhoneNumber(),
                    u.getEmailAddress(),
                    u.getAddress(),
                    u.getDateOfBirth())).reduce(outStr, String::concat);
            
            // Start writing into the database
            userDbWriter.write(outStr);
            
            // Close as soon as writing method is completed
            userDbWriter.close();
        }
        catch (FileNotFoundException ex_fnf) {
            // Exception will handled when file is not found
        }
        catch (IOException io_e) {
            // Exception will be handled when there is I/O problem.
        }
    }
    
    /**
     * Read from the database (see the variable fileName)
     */
    private void readFromDb() {
        try {
            // Initialize new database file
            File fw = new File(fileName);
            
            // Check whether the database exist and readable or not
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException(""); // Stop right here
            }
            
            // Initialize the database readre
            userDbReader = new Scanner(fw);
            
            // Loop through lines of the database
            while(userDbReader.hasNext()) {
                String user = userDbReader.nextLine();
                
                // '#' is the pseudo code, or the commented/disabled line
                if (!user.startsWith("#")) {
                    
                    /**
                     * Each line of the database, follows this structure (separated by CSV comma)
                     * 0 - Id
                     * 1 - username
                     * 2 - password in MD5
                     * 3 - name
                     * 4 - phoneNumber
                     * 5 - emailAddress
                     * 6 - address
                     * 7 - dateOfBirth
                     */
                    String[] userData = user.split(",");
                    
                    // Add new user into the list
                    // Note: Index 0 is the ID. No need to add
                    this.add(new User(
                            Integer.parseInt(userData[0]),
                            userData[1],
                            userData[2],
                            userData[3],
                            userData[4],
                            userData[5],
                            userData[6],
                            userData[7])
                    );
                }
            }
        }
        catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            // Handle exception on FileNotFound or Array out of bound or unable to parse integer
        }
    }
    
    /**
     * Hashing raw string into MD5
     * @param rawPassword as raw String
     * @return String in MD5
     */
    private String rawToMD5(String rawString) {
        String md5Str = "";
        
        try {
            // Initialize the MessageDigest
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Update the digest with the raw password
            md.update(rawString.getBytes());
            
            // Always lower case the MD5
            md5Str = DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            // Exception will be handled if unable to hashing the string into MD5
        }
        
        return (md5Str);
    }
    
    ///////////////////////////////////////////////////////
    ////// Selection-based methods
    ///////////////////////////////////////////////////////

    /**
     * Add new User
     * @param userName userName (or Account)
     * @param password Password of the userName (not name)
     * @param name Actual name of the user (Real name)
     * @param phone Phone number of the user
     * @param emailAddress Email address of the user
     * @param address Home address of the user
     * @param dateofBirth Date of Birth, follows as DD/MM/YYYY format
     * @return whether the add is valid. If returns -1, that mean user exists.
     * @throws java.lang.Exception in case of user is already existed 
     */
    
    public int addAccount(
            String userName, 
            String password, 
            String name, 
            String phone, 
            String emailAddress,
            String address,
            String dateofBirth) throws Exception {
        
        // the id
        int id = 0; 
        
        // Loop through the current database
        for (User u : this) {
            id += 1; // Add 1 for each id. The id starts with 0
            
            // If there is any account that equals to 
            if (userName.equals(u.getUserName())) {
                throw new Exception("User existed.");
            }
        }
        
        // If the id is equals to 1. that means, 
        if (id != -1) {
            this.add(
                new User(
                    id,
                    userName, 
                    rawToMD5(password), 
                    name, 
                    phone, 
                    emailAddress,
                    address,
                    dateofBirth)
            );
            
            this.writeToDb(); // Apply changes into the database
        }
        
        return (id);
    }
    
    /**
     * Let user login into the system
     * @param username userName (or Account) Password of the userName (not name).
     * @param password String of a raw password
     * @return a String of actual user name. If the string is empty, login failed
     */
    public boolean login(String username, String password) {
        // Return param (always initialize the return param)
        // See in 19950022400 - Section 5.5 for statement paragraphing
        boolean isLoginSuccess = false;
        
        // Hashing the password
        String hashPass = this.rawToMD5(password);
        
        // Loop through the array list to find the user
        for (User u : this) {
            String uName = u.getUserName();
            String uPass = u.getPassword();
            
            // Compare data
            if (uName.equals(username) && uPass.equals(hashPass)) {
                isLoginSuccess = true;
                break;
            }
        }
        
        // See in 19950022400 - Section 5.6 for return statement
        return (isLoginSuccess);
    }
    
    /**
     * Let user change password
     * @param userName userName (or Account)
     * @param oldRawPassword a String as old raw password
     * @param newRawPassword a String as new raw password
     * @return boolean as if change password success
     */
    public boolean changePassword(
            String userName, 
            String oldRawPassword,
            String newRawPassword) {
        
        boolean isChangeSuccess = false;
        // Hashing passwords into MD5
        String oldPassword = this.rawToMD5(oldRawPassword);
        String newPassword = this.rawToMD5(newRawPassword);
        
        // Loop through the array list to find the user. If matches, change its password 
        for (User s : this) {
            if (s.getUserName().equals(userName) && s.getPassword().equals(oldPassword)) {
                // Set password (in MD5)
                s.setPassword(newPassword);
                
                // Update back to the database file
                this.writeToDb();
                
                isChangeSuccess = true;
                
                break; // Stop when found
            }
        }
        
        return (isChangeSuccess);
    }
}
