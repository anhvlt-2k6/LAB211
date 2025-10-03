package short04;

import java.util.Scanner;
import short04.UserDb.UserList;

/**
 * Short 04 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class Short04 {
    
    // User input and user list
    private final Scanner sc;
    private final UserList userList;
    
    // Regex strings to validate email, phone number, and dob
    private final String accountValidator = "[A-Za-z0-9]{1,254}";
    private final String nameValidator = "[a-zA-Z0-9 ]{1,}";
    private final String emailValidator = "([A-Za-z0-9._-]{1,})@(([A-Za-z0-9]{1,}).[A-Za-z0-9]{2,})";
    private final String phoneValidator = "[0-9]{10,11}";
    private final String dobValidator = "([0-9]{2})/(([0-1]{1})([0-9]{1}))/([0-9]{4})";
    
    /**
     * Constructor of the program
     */
    public Short04() {
        sc = new Scanner(System.in);
        userList = new UserList();
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
                        System.out.print("Account:");
                        String userName = sc.nextLine().trim(); // trim to repalce spaces
                        if (!userName.matches(accountValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Account name should only contain letters and digits (up to 254 chars).");
                            break; // exit the loop and return back to user interface
                        }
                        
                        // Ask user to enter password
                        System.out.print("Password:");
                        String rawPassword  = sc.nextLine(); // Should not trim password
                        if (rawPassword == null || rawPassword.length() == 0) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Password should not be empty!");
                            break; // exit the loop and return back to user interface
                        }
                        
                        // Ask user to enter name
                        System.out.print("Name:");
                        String name = sc.nextLine().trim();
                        if (!name.matches(nameValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Name should only contains letters and spaces.");
                            break; // exit the loop and return back to user interface
                        }
                        
                        // Ask user to enter phone
                        System.out.print("Phone:");
                        String phoneNumber = sc.nextLine().trim();
                        if (!phoneNumber.matches(phoneValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Invalid phone number input. Should be 10-11 digits");
                            break; // exit the loop and return back to user interfaces
                        }
                        
                        // Ask user to enter email
                        System.out.print("Email:");
                        String emailAddress = sc.nextLine().trim(); 
                        if (!emailAddress.matches(emailValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Invalid email address input.");
                            break; // exit the loop and return back to user interfaces
                        }
                         
                        // Ask for address 
                        System.out.print("Address:");
                        String address = sc.nextLine().trim();
                        if (!address.matches(nameValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Address should not contain a pure name (only letters, digits and spaces are allowed)");
                            break; // exit the loop and return back to user interface
                        }
                        
                        // ask for date of birth
                        System.out.print("DOB:");
                        String dateOfBirth = sc.nextLine().trim();
                        if (!dateOfBirth.matches(dobValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Invalid DOB input.");
                            break; // exit the loop and return back to user interface
                        }
                        
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
                        System.out.print("Account: ");
                        String userName2 = sc.nextLine().trim();
                        if (!userName2.matches(accountValidator)) {
                            // Validate, and notify user if the user does not match the criteria
                            System.out.println("Account name should only contain letters and digits (up to 254 chars).");
                            break; // exit the loop and return back to user interface
                        }
                        
                        System.out.print("Password: ");
                        String password2 = sc.nextLine(); // Should not trim password
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
                                System.out.print("Old password:");
                                String oldRawPassword = sc.nextLine();

                                System.out.print("new password:");
                                String newRawPassword = sc.nextLine();

                                System.out.print("renew password:");
                                String newRawPassword2 = sc.nextLine();
                                
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
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        // New creation for short 03 program
        Short04 o4 = new Short04();
        
        // Display User interface
        o4.displayUserInterface();
    }
}
