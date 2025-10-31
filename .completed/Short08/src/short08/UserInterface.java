package short08;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Short 08 - User Interface
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-10-01
 */
public class UserInterface {
    
    /**
     * sc as user input handler
     * arr as array
     * intValidation for int input validation
     */
    private final Scanner sc;
    private final Array arr;
    
    private final String intValidation = "(-)?([0-9]{1,9})";
    
    /**
     * Constructor of the program
     */
    public UserInterface() {
        // Initialize of the user input and array
        sc = new Scanner(System.in);
        arr = new Array();
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
     * Option four - Find value in range
     */
    private void optionFour() {
        
        // Assume the lower bound and upper bound as invalid values
        int lower = Integer.MIN_VALUE + 1, upper = Integer.MIN_VALUE;
        
        // If lower > upper, ask user for input, validate, and if wrong, re-ask
        while (lower > upper) {
            // ask for string input first
            String lowerValue = this.enterAValue("Enter lower value: ");
            String upperValue = this.enterAValue("Enter upper value: ");
            
            // parse then
            lower = Integer.parseInt(lowerValue);
            upper = Integer.parseInt(upperValue);
            
            // if still wrong, print the error line
            if (lower > upper) {
                System.out.println("Lower value must smaller or equal upper value.");
            }
        }
        
        // get the arr in ArrayList
        ArrayList<Integer> arrinbound = arr.getInboud(lower, upper);
        
        // if empty, display for emptiness
        if (arrinbound.isEmpty()) {
            System.out.println("No value inbound found.");
        } else {
            String out = "";
            // else print for value. note that if its in default value, ignore
            for (int i : arrinbound) {
                if (i != Integer.MIN_VALUE) {
                    out += ("," + i);
                }
            }
            System.out.println(out.substring(1));
        }
    }
    
    /**
     * Print all values in array
     */
    private void optionThree() {
        //get the array
        int[] array = arr.getArr();
        
        // if array is null, display for its emptiness
        if (array == null) {
            System.out.println("The array is currently empty");
        } else {
            String out = "";
            // else print for value. note that if its in default value, ignore
            for (int i : array) {
                if (i != Integer.MIN_VALUE) {
                    out += ("," + i);
                }
            }
            
            if (out.length() == 0) {
                System.out.println("No values to display.");
            } else {
                System.out.println(out.substring(1));
            }
        }
    }
    
    /**
     * Find an index by its value
     */
    private void optionTwo() {
        // ask user to enter value to find
        String value = this.enterAValue("Enter a value to find: ").trim();
        
        // call the arr to find that value
        ArrayList<Integer> index = arr.indexofValue(Integer.parseInt(value));
        
        System.out.println("The index of found value: " + index);
    }
    
    /**
     * Option 1 - Add value
     */
    private void optionOne() {
        if (arr.getArrLength() > 99) {
            System.out.println("Array is over bounded.");
        } else {
            // Ask user for value
            String value = this.enterAValue("Enter a value: ");

            // try add that value into array
            if (arr.addValue(Integer.parseInt(value))) {
                System.out.println("New value is added");
            } else {
                System.out.println("Cannot add new value (Array out of bound)");
            }
        }
    }
    
    /**
     * User interface
     */
    public void userInterface() {
        while (true) {
            try {
                System.out.print(
                        "1. Add a value\n"
                        + "2. Search a value\n"
                        + "3. Print out an array\n"
                        + "4. Print out values in a range of inputted min and max values, inclusively.\n"
                        + "5. Sort the array\n"
                        + "(Others). Quit\n"
                        + "Choose an option: "
                );
                
                // Ask user for choice
                int choice = Integer.parseInt(sc.nextLine().trim());
                
                // Depends user's choice, try to execute that option
                switch (choice) {
                    case 1:
                        this.optionOne();
                        break;
                    case 2:
                        this.optionTwo();
                        break;
                    case 3:
                        this.optionThree();
                        break;
                    case 4:
                        this.optionFour();
                        break;
                    case 5:
                        arr.sort();
                        System.out.println("Sorting is completed!");
                        break;
                    default:
                        // Exit on default
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number 1-5 (or other to quit).");
            } catch (Exception e) {
                // print out any case of exceptional 
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}
