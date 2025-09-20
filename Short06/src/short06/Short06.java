package short06;

import java.util.Scanner;

/**
 * Short 06 - Main Program
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class Short06 {
    
    // Internal variables
    private final Scanner sc;
    private final IntArr arr;
    
    /**
     * Constructor of the main program
     */
    public Short06() {
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
                    System.out.print(String.format("Element[%d] = ", i));
                    arr.addNumber(Integer.parseInt(sc.nextLine()));
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
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        Short06 o6 = new Short06();
        o6.displayUserInterface();
    }
}