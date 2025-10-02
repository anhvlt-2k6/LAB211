package short10;

import java.util.Scanner;

/**
 * Short 10 - User Interface and Entry program
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class Short10 {
    
    // Scanner and String processing object
    private final Scanner sc;
    private final StringProcessor strPro;
    
    /**
     * Constructor of User Interface and Entry program
     */
    public Short10() {
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
        System.out.print("Enter a string: ");
        strPro.setUserInput(sc.nextLine());
        
        // Call to processing the string
        strPro.processString();
        
        // get character map (for counting)
        int[] charMap = strPro.getCharMap();
        
        // Loop through the character map
        for (int i = 0; i < charMap.length; i++) {
            // Skip for any value of char map is 0
            if (charMap[i] != 0) {
                // for display real char
                int realChar = i + 97;
                System.out.println(
                        String.format("\t%c: %d", realChar, charMap[i])
                );
            }
        }
    }
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        Short10 s10 = new Short10();
        s10.displayUI();
    }
}
