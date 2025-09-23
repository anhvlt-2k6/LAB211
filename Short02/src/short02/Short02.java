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
                int arr_length = Integer.parseInt(sc.nextLine().trim());
                
                // Check if the user enter wrong value
                if (arr_length > 0) {
                    nameList = new NameList(arr_length);
                    
                    // Ask user to enter values.
                    // Everytime user press enter, it accepts the value and move next.
                    System.out.println(String.format("Enter %d names", arr_length));
                    for (int i = 0; i < arr_length; i++) {
                        String value = "";
                        
                        // Loop for user re-input
                        while (value.isEmpty()) {
                            String userInput = sc.nextLine();
                            
                            // Validate if user input is really a name
                            if (userInput.matches("[A-Za-z0-9 ]{1,}")) {
                                value = userInput;
                                nameList.addElement(i, value);
                                break;
                            } else {
                                System.out.println("Invalid name");
                            }
                        }
                    }
                    
                    // Display for pre-sorting array
                    System.out.println("List input name:");
                    nameList.displayNameArray();
                    
                    // Sort the array 
                    nameList.sortNameArray();
                    
                    // Display for post-sorting array
                    System.out.println("List sort name:");
                    nameList.displayNameArray();
                } else {
                    System.out.println("Invalid input. Length of an array should be a positive number.");
                }
            } catch (NumberFormatException number_exception) {
                System.out.println("Not a number");
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
