package short11;

import java.util.Scanner;

/**
 * Short 11 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class UserInterface {
    
    // Scanner and String processing object
    private final Scanner sc;
    private final StringProcessor strPro;
    
    /**
     * Constructor of User Interface and Entry program
     */
    public UserInterface() {
        // Initialize the user input
        sc = new Scanner(System.in);
        // Initialize the string processor
        strPro = new StringProcessor();
    }
    
    /**
     * User interface
     */
    public void displayUI() {
        // Ask user to enter string
        System.out.print("Please enter string: ");
        strPro.setUserInput(sc.nextLine());
        
        // Print out the strings
        System.out.println("The old string: " + strPro.getOriginalStr() + "");
        System.out.println("The reversed string: " + strPro.getReversedStr() + "");
    }
}
