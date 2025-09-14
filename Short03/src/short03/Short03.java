package short03;

import java.util.Scanner;

/**
 * Short 03
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class Short03 {
    
    Scanner sc;
    Words words;
    
    /**
     * Constructor of the Program
     */
    public Short03() {
        sc = new Scanner(System.in);
        words = new Words();
    }
    
    /**
     * Display the user interface and handle user interaction
     */
    @SuppressWarnings("fallthrough")
    public void DisplayUserInterface() {
        System.out.print(
                "1. Create a new word\n" +
                "2. Edit a word\n" +
                "3. Look up meaning\n" +
                "4. Exit\n" +
                "Please choose number (1 – 4):"
        );
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.print("Enter a new word: ");
                    String word1 = sc.nextLine();
                    
                    System.out.print("Meaning: ");
                    String meaning1 = sc.nextLine();
                    
                    words.CreateWord(word1, meaning1);
                    break;
                case 2:
                    System.out.print("Enter a word to update: ");
                    String word2 = sc.nextLine();
                    
                    System.out.print("Meaning: ");
                    String meaning2 = sc.nextLine();
                    
                    words.EditWord(word2, meaning2);
                    break;
                case 3:
                    System.out.print("Enter a word to look up: ");
                    String word3 = sc.nextLine();
                    
                    words.LookUpMeaing(word3);
                    break;
                case 4:
                    System.exit(0);
                default:
                    break;
            }
        } catch (NumberFormatException number_exception) {
            //
        }
        
        System.exit(0);
    }
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        Short03 o3 = new Short03();
        o3.DisplayUserInterface();
    }
}
