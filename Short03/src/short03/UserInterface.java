/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package short03;

import java.util.Scanner;

/**
 * Short 03 - User Interface
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class UserInterface {
    
    /**
     * sc as user input
     * words as word database
     * wordValidation and meaningValidation as word and meaning validation
     */
    private final Scanner sc;
    private final Words words;
    
    private final String wordValidation = "[a-zA-Z ]{1,}";
    private final String meaningValidation = "[a-zA-Z0-9 :,?!]{1,}";
    
    /**
     * Constructor of the Program
     */
    public UserInterface() {
        sc = new Scanner(System.in);
        words = new Words();
    }
    
    /**
     * Display the user interface and handle user interaction
     */
    public void displayUserInterface() {
        // Loop through the interface
        while (true) {
            try {
                // Print out the user selection
                System.out.print(
                        "1. Create a new word\n" +
                        "2. Edit a word\n" +
                        "3. Look up meaning\n" +
                        "4. Exit\n" +
                        "Please choose number (1 – 4):"
                );

                // Get user input and parse from it
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        // Self-reading, please
                        System.out.print("Enter a new word: ");
                        String word1 = sc.nextLine();

                        // Validate word
                        if (!word1.matches(wordValidation)) {
                            System.out.println("That is not a word. Please re-validate!");
                            break;
                        }
                        
                        if (words.isWordDuplicated(word1)) {
                            System.out.println("A duplicated word is found");
                            break;
                        }
                        
                        System.out.print("Meaning: ");
                        String meaning1 = sc.nextLine();
                        
                        // Validate meaning
                        if (!meaning1.matches(meaningValidation)) {
                            System.out.println(
                                "A meaning should only contains letters, digits, and specials ('spaces', ':', ',', '?', '!'). "
                                + "Please re-validate!");
                            break;
                        }
                        
                        // Conditinal the word creation
                        if (words.createWord(word1, meaning1)) {
                            System.out.println(String.format("New word (%s) has been created", word1));
                        } else {
                            System.out.println(String.format("New word (%s) cannot be created", word1));
                        }
                        
                        break;
                    case 2:
                        // Self-reading, please
                        System.out.print("Enter a word to update: ");
                        String word2 = sc.nextLine();

                        // Validate word
                        if (!word2.matches(wordValidation)) {
                            System.out.println("That is not a word. Please re-validate!");
                            break;
                        }

                        System.out.print("Meaning: ");
                        String meaning2 = sc.nextLine();

                        // Validate meaning structure
                        if (!meaning2.matches(meaningValidation)) {
                            System.out.println(
                                "A meaning should only contains lettes, digits, and specials ('spaces', ':', ',', '?', '!')"
                                + "Please re-validate!");
                            break;
                        }

                        // Contional editing word
                        if (words.editWord(word2, meaning2)) {
                            System.out.println(String.format("The word (%s) has been modified", word2));
                        } else {
                            System.out.println(String.format("The word (%s) has NOT been modified. It either does NOT exist, or failed to modify due to an exception.", word2));
                        }
                        break;
                    case 3:
                        System.out.print("Enter a word to look up: ");
                        String word3 = sc.nextLine();
                        
                        // Validate word
                        if (!word3.matches(wordValidation)) {
                            System.out.println("That is not a word. Please re-validate!");
                            break;
                        }

                        words.lookUpMeaning(word3);
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } catch (NumberFormatException number_exception) {
                // Handle when unable to parse int from str
                System.out.println("Invalid input");
            }
        }
    }
    
}
