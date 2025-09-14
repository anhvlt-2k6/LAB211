package short02;

import java.util.Scanner;

/**
 * Short 02
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Short02 {

    
    /**
    For Exception handling: Should not use 'Throwable' or 'Exception'
        * Dangerous operation may not be catchable/handling.
        * Should leave it exceptional and let the program leaves
        * 
        * "These should normally be propagated to the outermost level because 
        * they generally indicate a program state from which normal  
        * operation cannot be recovered"
        *
        * See further in JPL Java Coding Standard P.73
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
        System.out.println("Enter the value of n");
        
        try {
            int arr_length = Integer.parseInt(sc.next());
            
            // Check if the user enter wrong value
            if (arr_length > 0) {
                nameList = new NameList(arr_length);
            } else {
                System.out.println(
                        "Invalid input. "
                        + "Length of an array should be a positive number."
                );
            }
            
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
            
        } catch (NumberFormatException number_exception) {
            //
        }
    }
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        Short02 o2 = new Short02();
        o2.userInterface();
    }
}
