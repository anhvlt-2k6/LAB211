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

public class UserList extends ArrayList<Users> {
    
    private static final long serialVersionUID = 1L;
    
    private final String emailValidator = "([A-Za-z0-9._-]{1,})@(([A-Za-z0-9]{1,}).[A-Za-z0-9]{2,})";
    private final String phoneValidator = "[0-9]{10,11}";
    private final String dobValidator = "([0-9]{2})\\/([0-9]{2})\\/([0-9]{4})";
    
    private final String fileName = "userdb.txt";
    
    private Scanner userDbReader;
    private FileWriter userDbWriter;
    
    ///////////////////////////////////////////////////////
    ////// Non-selection-based methods
    ///////////////////////////////////////////////////////
    
    public void writeToDb() {
        String outStr = "#Account,Password,Name,Phone,Email,Address,DOB";

        try {
            userDbWriter = new FileWriter(fileName);

            for (Users u : this) {
                outStr += String.format(
                        "\n%s,%s,%s,%s,%s,%s",
                        u.getUserName(),
                        u.getPassword(),
                        u.getName(),
                        u.getPhoneNumber(),
                        u.getEmailAddress(),
                        u.getDateOfBirth());
            }
            
            userDbWriter.write(outStr);
            userDbWriter.close();
        }
        catch (FileNotFoundException ex_fnf) {
            //
        }
        catch (IOException io_e) {
            //
        }
    }
    
    public void readFromDb() {
        try {
            File fw = new File(fileName);
            
            if (!fw.exists() && !fw.canRead()) {
                throw new FileNotFoundException(""); // Stop right here
            }
            
            userDbReader = new Scanner(fw);
            
            while(userDbReader.hasNext()) {
                String user = userDbReader.next();
                
                if (!user.startsWith("#")) {
                    
                    /**
                     * 0 - username
                     * 1 - password in MD5
                     * 2 - name
                     * 3 - phoneNumber
                     * 4 - emailAddress
                     * 5 - address
                     * 6 - dateOfBirth
                     */
                    String[] userData = user.split(",");
                    
                    this.addNewUser(
                            true,
                            userData[0],
                            userData[1],
                            userData[2],
                            userData[3],
                            userData[4],
                            userData[5],
                            userData[6]
                    );
                }
            }
        }
        catch (FileNotFoundException file_ex) {
            System.out.println("Unable to read file. No data.");
        }
        catch (ArrayIndexOutOfBoundsException arr_ex) {
            //
        }
    }
    
    private String rawToMD5(String rawPassword) {
        String md5Str = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(rawPassword.getBytes());
            
            // Always lower case the MD5
            md5Str = DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            //
        }
        
        return md5Str;
    }
    
    ///////////////////////////////////////////////////////
    ////// Selection-based methods
    ///////////////////////////////////////////////////////
    
    public boolean addNewUser(
            boolean isPasswordRaw,
            String userName, 
            String inputPassword, 
            String name, 
            String phoneNumber, 
            String emailAddress,
            String address,
            String dateofBirth) {
        boolean isAddValidationFailed = true;
        
        isAddValidationFailed = !emailAddress.matches(emailValidator) & isAddValidationFailed;
        isAddValidationFailed = !phoneNumber.matches(phoneValidator) & isAddValidationFailed;
        isAddValidationFailed = !dateofBirth.matches(dobValidator) & isAddValidationFailed;
        
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
            
            this.writeToDb();
        }
        
        return isAddValidationFailed; // return value if adding is failed
    }
    
    public String login(String userName, String rawPassword) {
        String actualName = "";
        
        String password = this.rawToMD5(rawPassword);
        
        for (Users u : this) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                actualName = u.getName();
                break;
            }
        }
        
        return actualName;
    }
    
    public void changePassword(
            String userName, 
            String oldRawPassword,
            String newRawPassword,
            String newRepeatPassword) {
        String password = this.rawToMD5(newRawPassword);
        for (Users s : this) {
            if (s.getUserName().equals(userName)) {
                s.setPassword(password);
                break;
            }
        }
    }
}
