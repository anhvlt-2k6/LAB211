package short06;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Short 06
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class Short06 {
    
    // Internal 
    private final Scanner sc;
    private int[] duplicatedArr;
    private final HashSet<Integer> nonDuplicatedArr;
    
    /**
     * Constructor of the main program
     */
    public Short06() {
        sc = new Scanner(System.in);
        nonDuplicatedArr = new HashSet<>();
    }
    
    /**
     * Display User Interface
     */
    public void displayUserInterface() {
        // Let user enter the size of array
        System.out.print("Please enter size of array: ");
        
        try {
            // Parse user input as the integer
            int length = Integer.parseInt(sc.nextLine());
            
            // Conditional the length - Only perform next step when length is valid
            if (length > 0) {
                // Initialize the array
                duplicatedArr = new int[length];
                
                // Let user input the array
                for (int i = 0; i < length; i++) {
                    System.out.print(String.format("Element[%d] = ", i));
                    duplicatedArr[i] = Integer.parseInt(sc.nextLine());
                }
                
                // Display the orginal array
                System.out.println("The original array:");
                for (int i : duplicatedArr) {
                    System.out.print(i + "\t");
                    nonDuplicatedArr.add(i);
                }
                
                // Display the non-duplicated-element array
                System.out.println("\nThe array after removing duplicate elements:");
                nonDuplicatedArr.forEach((i) -> {
                    System.out.print(i + "\t");
                });
            }
        } catch (NumberFormatException number_ex) {
            // Exception will handle when unable to parse int from str of user io
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