package short13;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Short 13 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-14
 */
public final class UserInterface {
    
    private final Scanner sc;
    
    private final CalcBackend calc;
    
    private final String numberValidator = "(-)?([0-9]{1,})(\\.[0-9]{1,})?";
    
    public UserInterface() {
        sc = new Scanner(System.in);
        calc = new CalcBackend();
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
    
    public void displayUI() {
        double mortageValue = Double.parseDouble(this.enterAValue("What is the value left on the mortage?", "Invalid input. It must be a positive number", numberValidator, true));
        while (mortageValue < 0) {
            System.out.println("Invalid input. It must be a positive number");
            mortageValue = Double.parseDouble(this.enterAValue("What is the value left on the mortage?", "Invalid input. It must be a positive number", numberValidator, true));
        }
        calc.setMortageValue(mortageValue);
        
        double percentage = Double.parseDouble(this.enterAValue("What is the annual interest rate of the loan, in percent?", "Invalid input. It must be a number (from 0 to 100)", numberValidator, true));
        while (percentage < 0 || percentage > 100) {
            System.out.println("Invalid input. It must be a number (from 0 to 100)");
            percentage = Double.parseDouble(this.enterAValue("What is the annual interest rate of the loan, in percent?", "Invalid input. It must be a number (from 0 to 100)", numberValidator, true));
        }
        calc.setPercentage(percentage);
        
        double monthlyPayment = Double.parseDouble(this.enterAValue("What is the monthly payment?", "Invalid input. It must be a positive number", numberValidator, true));
        while (monthlyPayment < 0) {
            System.out.println("Invalid input. It must be a positive number");
            monthlyPayment = Double.parseDouble(this.enterAValue("What is the value left on the mortage?", "Invalid input. It must be a positive number", numberValidator, true));
        }
        calc.setMonthlyPayment(monthlyPayment);
        
        ArrayList<double[]> values = calc.getValues();
        
        for (double[] v : values)  {
             if (v == null || v.length < 2) System.out.println("Null value"); // defensive
            System.out.printf("Payment: %.2f, Remaining balance: %.2f%n", v[0], v[1]);
        }
    }
}
