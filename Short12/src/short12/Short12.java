/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package short12;

import java.util.Scanner;

public class Short12 {

    private final Scanner sc;
    private final StringProcessing strPro;
    
    private final String binaryValidator = "[0-1]{1,32}";
    private final String octalValidator = "[0-7]{1,32}";
    private final String hexValidator = "[A-Fa-f0-9]{1,32}";
    
    public Short12() {
        sc = new Scanner(System.in);
        strPro = new StringProcessing();
    }
    
    /**
     * Enter a value that correspond the value that it called from
     * @param message as customized input message
     * @return String as validated user input
     */
    private String enterAValue(String message, String validation) {
        // If message is empty, assign with default message
        if (message.length() == 0) {
            message = "Enter a value: ";
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
                System.out.println("Invalid input. Please make sure the input is valid.");
            } else {
                // assign value
                value = valueUI;
            }
        }
        
        // return value
        return (value);
    }
    
    public void displayOptionThree() {
        System.out.println("Decimal number is:" + strPro.hexToDec(enterAValue("Enter a hexadecimal number: ", hexValidator)));
    }
    
    public void displayOptionTwo() {
        System.out.println("Decimal number is:" + strPro.octToDec(enterAValue("Enter a octal number: ", octalValidator)));
    }
    
    public void displayOptionOne() {
        System.out.println("Decimal number is:" + strPro.binToDec(enterAValue("Enter a binary number: ", binaryValidator)));
    }
    
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
                
                int choice = Integer.parseInt(sc.nextLine());
                
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
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    } 
    
    public static void main(String[] args) {
        Short12 s12 = new Short12();
        s12.displayUserInterface();
    }
    
}
