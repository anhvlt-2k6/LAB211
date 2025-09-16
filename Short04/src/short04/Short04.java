package short04;

import java.util.Scanner;
import short04.UserDb.UserList;

public class Short04 {
    
    Scanner sc;
    UserList userList;
    
    public Short04() {
        sc = new Scanner(System.in);
        userList = new UserList();
    }
    
    public void DisplayUserInterface() {
        System.out.print(
                "============ Login Program =========\n" +
                "1. Add User\n" +
                "2. Login\n" +
                "3) Exit\n" +
                "Please choice one option:"
        );
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.println("---------- Add User --------");
                    
                    System.out.print("Account:");
                    String userName = sc.nextLine();
                    
                    System.out.print("Password:");
                    String rawPassword  = sc.nextLine();
                    
                    System.out.print("Name:");
                    String name = sc.nextLine();
                    
                    System.out.print("Phone:");
                    String phoneNumber = sc.nextLine();
                    
                    System.out.print("Email:");
                    String emailAddress = sc.nextLine();
                    
                    System.out.print("Address:");
                    String address = sc.nextLine();
                    
                    System.out.print("DOB:");
                    String dateOfBirth = sc.nextLine();
                    
                    if (userList.addNewUser(true, userName, rawPassword, name, phoneNumber, emailAddress, address, dateOfBirth)) {
                        System.out.print("Please validate your input.");
                    }                    
                    break;
                case 2:
                    System.out.println("------------- Login ----------------");
                    
                    System.out.print("Account: ");
                    String userName2 = sc.nextLine();
                    
                    System.out.print("Password: ");
                    String password2 = sc.nextLine();
                    
                    String actualName = userList.login(userName2, password2);
                    if (actualName.length() != 0) {
                        System.out.print(
                                String.format(
                                    "------------ Wellcome -----------\n" +
                                    "Hi %s, do you want change\n" +
                                    "password now? Y/N:", actualName));
                        
                        String isChangePassword = sc.nextLine();
                        
                        if (isChangePassword.toLowerCase().equals("Y")) {
                            
                        } else {
                            
                        }
                    } else {
                        System.out.print("Login failed.");
                    }
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException num_ex) {
            //
        }
        
        System.exit(0);
    }
    
    public static void main(String[] args) {
        Short04 o4 = new Short04();
        
        o4.DisplayUserInterface();
    }
}
