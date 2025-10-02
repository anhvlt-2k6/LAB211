package short05;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Short 05 - Main Program
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class Short05 {

    // Internal variables for input and data processing
    private final Scanner sc;
    private final AnalysisString ansysStr;
    
    // Internal variables for getting result
    private HashMap<String, List<Integer>> getNumber;
    private HashMap<String, StringBuilder> getCharacter;
    
    /**
     * Constructor of the "main" program
     */
    public Short05() {
        sc = new Scanner(System.in);
        ansysStr = new AnalysisString();
    }
    
    /**
     * Display user interface
     */
    public void displayUserInterface() {
        System.out.print(
                "===== Analysis String program ====\n" +
                "Input String: ");
        
        // Take user input
        String input = sc.nextLine();
        
        // get number analysis result
        getNumber = ansysStr.getNumber(input);
        
        // get character analysis result
        getCharacter = ansysStr.getCharacter(input);

        System.out.print(
                "-----Result Analysis------\n" +
                "Square Numbers: " + getNumber.get("square") + "\n" +
                "Odd Numbers: " + getNumber.get("odd") + "\n" +
                "Even Numbers: " + getNumber.get("even") + "\n" +
                "All Numbers: " + getNumber.get("all") + "\n" +
                "Uppercase Characters: " + getCharacter.get("upper") + "\n" +
                "Lowercase Characters: " + getCharacter.get("lower") + "\n" +
                "Special Characters: " + getCharacter.get("special") + "\n" +
                "All Characters: " + getCharacter.get("all") + "\n"
        );

        System.exit(0);
    }
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        Short05 o5 = new Short05();
        o5.displayUserInterface();
    }    
}
