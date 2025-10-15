package short14;

import java.util.Scanner;

public class UserInterface {
    
    private final Scanner sc;
    private final NumberProcessorBackend numbackend;
    
    private final String numberValidator = "[0-9]{1,}";
    
    public UserInterface() {
        sc = new Scanner(System.in);
        numbackend = new NumberProcessorBackend();
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message, String error, String validation, boolean isTrim) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
        }
        
        if (error.length() == 0) {
            error = "Invalid Input.";
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
     * Option three - get sum of digits
     */
    private void optionThree() {
        String s = this.enterAValue("Number of tested: ", "Must be a positive number", numberValidator, true);
        System.out.println("Sum of digits: " + numbackend.sumofDigits(s));
    }
    
    /**
     * Option two - check if fib
     */
    private void optionTwo() {
        int n = Integer.parseInt(this.enterAValue("Number of tested: ", "Must be a positive number", numberValidator, true));
        while (n > 1000) {
            System.out.println("Must be a number smaller than 1001.");
            n = Integer.parseInt(this.enterAValue("Number of tested: ", "Must be a positive number", numberValidator, true));
        }
        
        System.out.println(
                numbackend.isFib(n) ? "It's a Fibonacci term" : "It's not a Fibonacci term"
        );
    }
    
    /**
     * Option one - get number of primes
     */
    private void optionOne() {
        int limit = Integer.parseInt(this.enterAValue("Number of primes: ", "Must be a positive number", numberValidator, true));
        while (limit > 50) {
            System.out.println("Must be a number smaller than 51.");
            limit = Integer.parseInt(this.enterAValue("Number of primes: ", "Must be a positive number", numberValidator, true));
        }
        
        System.out.println(numbackend.getPrimes(limit));
    }
    
    
    
    /**
     * Display user interface
     */
    public void displayUserInterface() {
        while(true) {
            try {
                System.out.print(
                        "1-The first primes\n"
                        + "2-Fibonacci element\n"
                        + "3-Sum of digits\n"
                        + "Choose an option:"
                );
                
                // Try parsing number
                int choice = Integer.parseInt(sc.nextLine());
                
                // For each case, try to adopt with that case
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
