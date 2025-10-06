package short12;

import java.util.Scanner;

/**
 * Short 12 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class UserInterface {
    
    /*
    sc as scanner
    strPro as String processor
    
    binaryValidator, octalValidator, and hexValidator as validator
    */
    private final Scanner sc;
    private final StringProcessing strPro;
    
    private final String binaryValidator = "[0-1]{1,64}";
    private final String octalValidator = "[0-7]{1,64}";
    private final String hexValidator = "[A-Fa-f0-9]{1,64}";
    
    /**
     * Constructor of the User Interface
     */
    public UserInterface() {
        // Initialize the scanner
        sc = new Scanner(System.in);
        
        // Initialize the string processor
        strPro = new StringProcessing();
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message, String validation, String error) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
        }
        
        if (error.length() == 0) {
            error = "Invalid input. Please make sure the input is valid.";
        }
        
        // Assume the value is empty string
        String value = "";
        // Loop for correct format
        while (!value.matches(validation)) {
            System.out.print(message);
            String valueUI = sc.nextLine().trim(); // Ask user for input
            
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
     * Display option 3 - hex to decimal
     */
    private void displayOptionThree() {
        System.out.println("Decimal number is:" + strPro.hexToDec(enterAValue("Enter a hexadecimal number: ", hexValidator, "Only allow A-Z and 0-9 (case insensitive, max 64 characters).")));
    }
    
    /**
     * Display option 2 - octal to decimal
     */
    private void displayOptionTwo() {
        System.out.println("Decimal number is:" + strPro.octToDec(enterAValue("Enter a octal number: ", octalValidator, "Only allow 0-7. (Max 64 characters)")));
    }
    
    /**
     * Display option 1 - binary to decimal
     */
    private void displayOptionOne() {
        System.out.println("Decimal number is:" + strPro.binToDec(enterAValue("Enter a binary number: ", binaryValidator, "Only allow 0 and 1. (Max 64 characters)")));
    }
    
    /**
     * Display user interface
     */
    public void displayUserInterface() {
        while(true) {
            try {
                System.out.print(
                        "1. Convert binary number to decimal number\n" +
                        "2. Convert octal number to decimal number\n" +
                        "3. Convert hexadecimal number to decimal number\n" +
                        "4. Exit\n" +
                        "Please choose number (1 – 4):"
                );
                
                // Try parsing number
                int choice = Integer.parseInt(sc.nextLine());
                
                // For each case, try to adopt with that case
                switch (choice) {
                    case 1:
                        this.displayOptionOne();
                        break;
                    case 2:
                        this.displayOptionTwo();
                        break;
                    case 3:
                        this.displayOptionThree();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        // Default case is outbound, invalid it instead
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (NumberFormatException e) {
                // In case of unable to parse number
                System.out.println(e.getLocalizedMessage());
            }
        }
    } 
}
