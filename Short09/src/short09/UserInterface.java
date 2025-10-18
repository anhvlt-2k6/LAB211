package short09;

import java.util.Scanner;

/**
 * Short 09 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class UserInterface {
    
    // Initialize user input and array object
    private final Scanner sc;
    private Array arr;
    
    // String to validate if it is the integer validation
    private final String intValidation = "(-)?([0-9]{1,12})";
    
    /**
     * Constructor of program
     */
    public UserInterface() {
        // Initialize the scanner and array object
        sc = new Scanner(System.in);
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
        }
        
        // Assume the value is empty string
        String value = "";
        // Loop for correct format
        while (!value.matches(intValidation)) {
            System.out.print(message);
            String valueUI = sc.nextLine().trim(); // Ask user for input
            
            // Validate user input, if match with the criteria, assign with the 
            // return value, if not, asking user again
            if (!valueUI.matches(intValidation)) {
                // Notify user
                System.out.println("Invalid input. Please make sure the input is a number.");
            } else {
                // assign value
                value = valueUI;
            }
        }
        
        // return value
        return (value);
    }
    
    /**
     * Display all contents in the array
     */
    public void displayArr() {
        System.out.println();
        for (int i : arr.getArr()) {
            if (i != Integer.MAX_VALUE) {
                System.out.print(i + "\t");
            }
        }
    }
    
    /**
     * Display the user interface
     */
    public void displayUserInterface() {
        try {
            // Enter size of array
            String inputStr = this.enterAValue("Please enter size of array: ");    
            int input = Integer.parseInt(inputStr);
            
            arr = new Array(input);
            
            // for each index, ask user for input
            for (int i = 0; i < input; i++) {
                // Enter value for that input
                String valueStr = this.enterAValue("Enter element [" + i + "]: ");
                arr.addValue(i, Integer.parseInt(valueStr));
            }
            
            arr.sort();
            
            // Display the array after sorting
            System.out.println("The array after sorting: ");
            this.displayArr();
            
            // Ask user to enter new value
            String newValueStr = this.enterAValue("\nPlease enter new value: ");
            int newValue = Integer.parseInt(newValueStr);
            arr.addNewInorder(newValue);
            
            // Display the new array
            System.out.println("New array:");
            this.displayArr();
        } catch (NumberFormatException e) {
            // In case user enter invalid number and unable to parse
            System.out.println(e.getLocalizedMessage());
        }
    }
}
