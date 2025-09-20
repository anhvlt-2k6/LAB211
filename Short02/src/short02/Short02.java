package short02;

import java.util.Scanner;

/**
 * Short 02 - Main entry
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Short02 {
    
    /**
    * Global variables
    * sc - SCanner (General I/O for Program)
    * nameList - NameList
    */
    
    private NameList nameList;
    private final Scanner sc;
    
    /**
     * Constructor of the "entry" class.
     * Initialize the user input (Scanner)
     */
    public Short02() {
        sc = new Scanner(System.in);
    }
    
    /**
     * Display User Interface
     */
    public void userInterface() {
        while (true) {
            try {
                System.out.println("Enter the value of n");
                int arr_length = Integer.parseInt(sc.next());

                // Check if the user enter wrong value
                if (arr_length > 0) {
                    nameList = new NameList(arr_length);
                    
                    // Ask user to enter values.
                    // Everytime user press enter, it accepts the value and move next.
                    System.out.println(String.format("Enter %d names", arr_length));
                    for (int i = 0; i < arr_length; i++) {
                        String value = sc.next();
                        nameList.AddElement(i, value);
                    }

                    System.out.println("List input name:");
                    nameList.DisplayNameArray();

                    System.out.println("List sort name:");
                    nameList.SortNameArray();
                    nameList.DisplayNameArray();
                    
                    break;
                } else {
                    System.out.println("Invalid input. Length of an array should be a positive number.");
                }
            } catch (NumberFormatException number_exception) {
                System.out.print("Not a number");
            }
        }
    }
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        // New creation for short02 program
        Short02 o2 = new Short02();
        
        // Display user interface
        o2.userInterface();
    }
}
