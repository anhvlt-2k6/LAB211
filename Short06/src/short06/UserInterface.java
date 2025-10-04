package short06;

import java.util.Scanner;

/**
 * Short 06 - User Interface
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class UserInterface {
    
    // Internal variables
    private final Scanner sc;
    private final IntArr arr;
    
    /**
     * Constructor of the main program
     */
    public UserInterface() {
        sc = new Scanner(System.in);
        arr = new IntArr();
    }
    
    /**
     * Display User Interface
     */
    public void displayUserInterface() {
        try {
            // Let user enter the size of array
            System.out.print("Please enter size of array: ");
            
            // Parse user input as the integer
            int length = Integer.parseInt(sc.nextLine());
            
            // Conditional the length - Only perform next step when length is valid
            if (length > 0) {
                
                // Let user input the array
                for (int i = 0; i < length; i++) {
                    String addNumber = "";
                    while (!addNumber.matches("[0-9]{1,}")) {
                        System.out.print(String.format("Element[%d] = ", i));
                        addNumber = sc.nextLine();
                            
                        if (!addNumber.matches("[0-9]{1,}")) {
                            System.out.println("Invalid input. Must be a number (no float)");
                        }
                    }

                    arr.addNumber(Integer.parseInt(addNumber));
                }
                
                // Display the orginal array
                System.out.println("The original array:");
                arr.getDuplicatedArr().forEach((i) -> {
                    System.out.print(i + "\t");
                });
                
                // Display the non-duplicated-element array
                System.out.println("\nThe array after removing duplicate elements:");
                arr.getNonDuplicatedArr().forEach((i) -> {
                    System.out.print(i + "\t");
                });
            } else {
                System.out.print("Length must be a number and larger than 0");
            }
        } catch (NumberFormatException number_ex) {
            // Exception will handle when unable to parse int from str of user io
            System.out.println("Error: " + number_ex.getLocalizedMessage());
        }
    }
    
}
