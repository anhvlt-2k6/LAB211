package SampleLab;

import java.util.Scanner;

/**
 * Sample Lab
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-11
 */
public class SampleLab {
    ArrClass arr;
    Scanner sc;
    
    /**
     * Initialize global variables 
     * In this constructor, sc (Scanner for short) is initialized as System.in (which is taken input from user PnP devices) 
     */
    public SampleLab() {
        sc = new Scanner(System.in);
    }
    
    /**
     * Display the user interface
     */
    public void displayUserInterface() {
        
        System.out.print("Enter number of array: ");
        
        /**
         * Why try-catch? Since the user input **MAY** contain trash input.
         * Also, user can enter negative number (invalid, see in the constructor of ArrClass.java)
         */
        try {
            int size = Integer.parseInt(sc.next());
            arr = new ArrClass(size);
            
            System.out.println("\nUnsorted Array: ");
            arr.displayArr();

            arr.sortArray();

            System.out.println("\nSorted Array: ");
            arr.displayArr();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
    
    /**
     * The "entry" of the program.
     * Since the program is object-oriented, it only should run within the object.
     * @param args - take arguments but no action performed
     */
    public static void main(String[] args) {
        SampleLab so = new SampleLab();
        so.displayUserInterface();
    }
}
