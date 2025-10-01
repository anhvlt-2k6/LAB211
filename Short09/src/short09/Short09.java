package short09;

import java.util.Scanner;

public class Short09 {

    private final Scanner sc;
    private final Array arr;
    
    private final String intValidation = "(-)?([0-9]{1,12})";
    
    public Short09() {
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
    
    public void displayUserInterface() {
        try {
            String inputStr = this.enterAValue("Please enter size of array: ");    
            int input = Integer.parseInt(inputStr);
            
            for (int i = 0; i < input; i++) {
                String valueStr = this.enterAValue("Enter element [" + i + "]: ");
                arr.addElement(Integer.parseInt(valueStr));
            }
            
            System.out.println("The array after sorting:\n" + arr.getArr());
            
            String newValueStr = this.enterAValue("Please enter new value: ");
            int newValue = Integer.parseInt(newValueStr);
            arr.addElement(newValue);
            
            System.out.println("New array:\n" + arr.getArr());
        } catch (NumberFormatException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static void main(String[] args) {
        Short09 s09 = new Short09();
        s09.displayUserInterface();
    }
}
