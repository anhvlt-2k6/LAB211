package short05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Short05 {

    private final Scanner sc;
    private final AnalysisString ansysStr;
    
    private HashMap<String, ArrayList<Integer>> getNumber;
    private HashMap<String, StringBuilder> getCharacter;
    
    public Short05() {
        sc = new Scanner(System.in);
        ansysStr = new AnalysisString();
    }
    
    public void displayUserInterface() {
        System.out.print(
                "===== Analysis String program ====\n" +
                "Input String: ");
        
        String input = sc.nextLine();

        getNumber = ansysStr.getNumber(input);
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
    
    public static void main(String[] args) {
        Short05 o5 = new Short05();
        o5.displayUserInterface();
    }    
}
