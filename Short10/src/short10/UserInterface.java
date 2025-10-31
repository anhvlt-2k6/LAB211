package short10;

import java.util.Scanner;

/**
 * Short 10 - User Interface
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
        // Ask user to enter string (no verification needed)
        System.out.print("Enter a string: ");
        strPro.setUserInput(sc.nextLine());

        // get character map (for counting)
        int[] charMap = strPro.getCharMap();

        // Loop through the character map
        for (int i = 0; i < charMap.length; i++) {
            // Skip for any value of char map is 0
            if (charMap[i] != 0) {
                // for display real char
                char realChar = (char)(i + 'a');
                System.out.println(
                        String.format("\t%c: %d", realChar, charMap[i])
                );
            }
        }
    }
}
