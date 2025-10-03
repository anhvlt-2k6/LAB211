package short04;

import java.util.Scanner;
import short04.UserDb.UserList;

/**
 * Short 04 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class UserInterface {
    
    // User input and user list
    private final Scanner sc;
    private final UserList userList;
    
    // Regex strings to validate email, phone number, and dob
    private final String accountValidator = "[A-Za-z0-9]{1,254}";
    private final String nameValidator = "[a-zA-Z ]{1,}";
    private final String emailValidator = "([A-Za-z0-9._-]{1,})@(([A-Za-z0-9]{1,}).[A-Za-z0-9]{2,})";
    private final String phoneValidator = "[0-9]{10,11}";
    private final String dobValidator = "([0-9]{2})/(([0-1]{1})([0-9]{1}))/([0-9]{4})";
    private final String passValidator = ".+";
    
    /**
     * Constructor of the program
     */
    public UserInterface() {
        sc = new Scanner(System.in);
        userList = new UserList();
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message, String validation, String error, boolean isTrim) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
        }
        
        // Assume the value is empty string
        String value = "";
        // Loop for correct format
        while (!value.matches(validation)) {
            System.out.print(message);
            String valueUI = (isTrim) ? (sc.nextLine().trim()) : (sc.nextLine());
            
            // Validate user input, if match with the criteria, assign with the 
            // return value, if not, asking user again
            if (!valueUI.matches(validation)) {
                // Notify user
                System.out.println(error);
            } else {
                // assign value
                value = valueUI;
            }
        }
        
        // return value
        return (value);
    }
    
    /**
     * Display user interface
     */
    public void displayUserInterface() {
        while (true) {
            try {
                System.out.print(
                        "============ Login Program =========\n" +
                        "1. Add User\n" +
                        "2. Login\n" +
                        "3) Exit\n" +
                        "Please choice one option:"
                );

                // Parse user input as the integer
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("---------- Add User --------");
                        
                        // Ask user to enter user name
                        String userName = this.enterAValue("Account: ", accountValidator, "Account should only be digits and letters", true);
                        
                        if (userList.isUserExisted(userName)) {
                            System.out.println("Account existed.");
                            break;
                        }
                        
                        // Ask user to enter password
                        String rawPassword = this.enterAValue("Password: ", passValidator, "Password should not be empty", false);
                        
                        // Ask user to enter name
                        String name = this.enterAValue("Name: ", accountValidator, "Name should only be letters and spaces.", true);
                        
                        // Ask user to enter phone
                        String phoneNumber = this.enterAValue("Phone: ", phoneValidator, "Invalid phone number input. Should be 10-11 digits.", true);
                        
                        // Ask user to enter email
                        String emailAddress = this.enterAValue("Email: ", emailValidator, "Invalid email address input. Must be in format a@a.com", true);
                        
                        // Ask for address 
                        String address = this.enterAValue("Address: ", nameValidator, "Address should not contain a pure name (only letters, digits and spaces are allowed)", true);

                        // ask for date of birth
                        String dateOfBirth = this.enterAValue("DOB: ", dobValidator, "Invalid DOB input. Should be in DD/MM/YYYY format", true);
                        
                        // add new user into userList.
                        // If it returns -1, the user existed. See function help of addNewUser
                        if (userList.addAccount(userName, rawPassword, name, phoneNumber, emailAddress, address, dateOfBirth) == -1) {
                            System.out.println("Unable to add user. The user existed.");
                        } else {
                            System.out.println("Successfully added user: " + userName);
                        }
                        
                        break;
                    case 2:
                        System.out.println("------------- Login ----------------");
                        
                        // Ask user to enter for account
                        String userName2 = this.enterAValue("Account: ", accountValidator, "Account should only be digits and letters", true);
                        
                        // Ask for password
                        String password2 = this.enterAValue("Password: ", passValidator, "Password should not be empty", false);
                        
                        // If the string is empty, login is failed
                        if (userList.login(userName2, password2)) {
                            System.out.print(
                                    String.format(
                                        "------------ Wellcome -----------\n" +
                                        "Hi %s, do you want change\n" +
                                        "password now? Y/N:", userName2));
                            
                            // ask user if they want to change password
                            String isChangePassword = sc.nextLine();
                            if (isChangePassword.toLowerCase().equals("y")) {
                                // Enter old, new and reenter new password
                                String oldRawPassword = this.enterAValue("Old Password: ", passValidator, "Password should not be empty", false);
                                String newRawPassword = this.enterAValue("New Password: ", passValidator, "Password should not be empty", false);
                                String newRawPassword2 = this.enterAValue("Repeat Password: ", passValidator, "Password should not be empty", false);
                                
                                // Check if new password and repeat password are the same
                                if (newRawPassword.equals(newRawPassword2) &&
                                        userList.changePassword(userName2, oldRawPassword, newRawPassword)) {
                                    System.out.println("Changed password successfully!");
                                } else {
                                    System.out.println("Failed changing password.");
                                }
                            }
                        } else {
                            // Notify for failed login
                            System.out.println("Login failed.");
                        }
                        break;
                    case 3:
                        // Exit the program
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException num_ex) {
                // Exception will be handled if unable to parse
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }
}
