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
 * Short 04
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
@SuppressWarnings("serial")
public class UserList extends ArrayList<Users> {
    
    // Regex strings to validate email, phone number, and dob
    private final String emailValidator = "([A-Za-z0-9._-]{1,})@(([A-Za-z0-9]{1,}).[A-Za-z0-9]{2,})";
    private final String phoneValidator = "[0-9]{10,11}";
    private final String dobValidator = "([0-9]{2})\\/([0-9]{2})\\/([0-9]{4})";
    
    // Database file name
    private final String fileName = "userdb.txt";
    
    // File reader and writer
    private Scanner userDbReader;
    private FileWriter userDbWriter;
    
    ///////////////////////////////////////////////////////
    ////// Non-selection-based methods
    ///////////////////////////////////////////////////////

    /**
     * Write content from the ArrayList into the database (see the variable fileName)
     */
    
    public void writeToDb() {
        // Pseudo line (First Line) as the header
        String outStr = "#Account,Password,Name,Phone,Email,Address,DOB";

        try {
            // Initialize new database writer
            userDbWriter = new FileWriter(fileName);
            
            // Write into structure. See the Pseudo line above.
            outStr = this.stream().map((u) -> String.format(
                    "\n%s,%s,%s,%s,%s,%s,%s",
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
    public void readFromDb() {
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
                     * 0 - username
                     * 1 - password in MD5
                     * 2 - name
                     * 3 - phoneNumber
                     * 4 - emailAddress
                     * 5 - address
                     * 6 - dateOfBirth
                     */
                    String[] userData = user.split(",");
                    
                    // Add new user into
                    this.add(new Users(
                            userData[0],
                            userData[1],
                            userData[2],
                            userData[3],
                            userData[4],
                            userData[5],
                            userData[6])
                    );
                }
            }
        }
        catch (FileNotFoundException | ArrayIndexOutOfBoundsException ex) {
            // Handle exception on FileNotFound or Array out of bound
        }
    }
    
    /**
     * Hashing raw password into MD5
     * @param rawPassword as raw String password
     * @return String as password in MD5
     */
    private String rawToMD5(String rawPassword) {
        String md5Str = "";
        
        try {
            // Initialize the MessageDigest
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Update the digest with the raw password
            md.update(rawPassword.getBytes());
            
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
     * @param isPasswordRaw whether the password is raw or hashed
     * @param userName userName (or Account)
     * @param inputPassword Password of the userName (not name)
     * @param name Actual name of the user (Real name)
     * @param phoneNumber Phone number of the user
     * @param emailAddress Email address of the user
     * @param address Home address of the user
     * @param dateofBirth Date of Birth, follows as DD/MM/YYYY format
     * @return whether or not the input is valid
     */
    
    public boolean addNewUser(
            boolean isPasswordRaw,
            String userName, 
            String inputPassword, 
            String name, 
            String phoneNumber, 
            String emailAddress,
            String address,
            String dateofBirth) {
        
       // Validate if the input is actually valid
        boolean isAddValidationFailed =
            userName == null || userName.isEmpty() ||
            emailAddress == null || !emailAddress.matches(emailValidator) ||
            phoneNumber == null || !phoneNumber.matches(phoneValidator) ||
            dateofBirth == null || !dateofBirth.matches(dobValidator);
        
        // If the input is valid, add user
        if (!isAddValidationFailed) {
            this.add(
                    new Users(
                        userName, 
                        (isPasswordRaw) ? rawToMD5(inputPassword) : inputPassword, 
                        name, 
                        phoneNumber, 
                        emailAddress,
                        address,
                        dateofBirth)
            );
            
            this.writeToDb(); // Apply changes into the database
        }
        
        // See in 19950022400 - Section 5.6 for return statement
        return (isAddValidationFailed); // return value if adding is failed or not
    }
    
    /**
     * Let user login into the system
     * @param userName userName (or Account) Password of the userName (not name).
     * @param rawPassword String of a raw password
     * @return a String of actual user name. If the string is empty, login failed
     */
    public String login(String userName, String rawPassword) {
        // Return param (always initialize the return param)
        // See in 19950022400 - Section 5.5 for statement paragraphing
        String actualName = "";
        
        // Hashing the password
        String password = this.rawToMD5(rawPassword);
        
        // Loop through the array list to find the user
        for (Users u : this) {
            String uName = u.getUserName();
            String uPass = u.getPassword();
            if (uName.equals(userName) && uPass.equals(password)) {
                actualName = u.getName();
                break;
            }
        }
        
        // See in 19950022400 - Section 5.6 for return statement
        return (actualName);
    }
    
    /**
     * Let user change password
     * @param userName userName (or Account)
     * @param oldRawPassword a String as old raw password
     * @param newRawPassword a String as new raw password
     */
    public void changePassword(
            String userName, 
            String oldRawPassword,
            String newRawPassword) {
        
        // Hashing passwords into MD5
        String oldPassword = this.rawToMD5(oldRawPassword);
        String newPassword = this.rawToMD5(newRawPassword);
        
        // Loop through the array list to find the user. If matches, change its password 
        for (Users s : this) {
            if (s.getUserName().equals(userName) && s.getPassword().equals(oldPassword)) {
                // Set password (in MD5)
                s.setPassword(newPassword);
                
                // Update back to the database file
                this.writeToDb();
                
                break; // Stop when found
            }
        }
    }
}
