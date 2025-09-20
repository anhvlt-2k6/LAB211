package short05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Short 05 - String Array Handler
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public class AnalysisString {
    
    /**
     * Get numbers in the input string
     * @param input as the user string input into the program
     * @return a HashMap, follows this structure
     *         - "all" (ArrayList) an array list contains all numbers
     *         - "square" (ArrayList) an array list contains squared numbers
     *         - "odd" (ArrayList) an array list contains odd numbers
     *         - "even" (ArrayList) an array list contains even numbers
     */
    public HashMap<String, List<Integer>> getNumber(String input) {
        // Return param (always initialize the return param)
        // See in 19950022400 - Section 5.5 for statement paragraphing
        HashMap<String, List<Integer>> result = new HashMap<>();
        
        // Anaysis values, prefix as shown above
        List<Integer> allNumbers = new ArrayList<>();
        List<Integer> squareNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();
        
        // Add all integer into a list (further analysis)
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(input);
        
        // Loop through the matcher
        while (matcher.find()) {
            try {
                // Parse int from str
                int val = Integer.parseInt(matcher.group());
                
                // Add new number
                allNumbers.add(val);

                // Conditional check for squared number 
                if (val >= 0) {
                    // Casting of Double to Integer (~Math.floor)
                    int r = (int) Math.sqrt(val);
                    
                    if (r * r == val) {
                        squareNumbers.add(val);
                    }
                }

                // Check if odd or even number
                if (val % 2 == 0) {
                    evenNumbers.add(val);
                } else {
                    oddNumbers.add(val);
                }
            } catch (NumberFormatException ex) {
                // Handle exception in case of unable to parse number from string
            }
        }

        // Put values into "keys"
        // See explaination above structure
        result.put("all", allNumbers);
        result.put("square", squareNumbers);
        result.put("odd", oddNumbers);
        result.put("even", evenNumbers);

        // See in 19950022400 - Section 5.6 for return statement
        return (result);
    }
    
    /**
     * Get chars in the input string
     * @param input as the user string input into the program
     * @return a HashMap, follows this structure
     *         - "all" All Characters (except digits)
     *         - "upper" All uppercase characters
     *         - "lower" All lowercase characters
     *         - "special" All special characters
     */
    public HashMap<String, StringBuilder> getCharacter(String input) {
         // Return param (always initialize the return param)
        // See in 19950022400 - Section 5.5 for statement paragraphing
        HashMap<String, StringBuilder> result = new HashMap<>();
        
        // Analysis values, prefix as shown above
        StringBuilder allChars = new StringBuilder();
        StringBuilder uppercaseChars = new StringBuilder();
        StringBuilder lowercaseChars = new StringBuilder();
        StringBuilder specialChars = new StringBuilder();
        
        // Loop from the start 'til end of the input string
        for (int idx = 0; idx < input.length(); idx++) {
            char c = input.charAt(idx);
        
            // Check whether the character meet any above condition
            // Note: The "allChars" should not include digits, don't move it outside
            if (Character.isUpperCase(c)) {
                allChars.append(c);
                uppercaseChars.append(c);
            } else if (Character.isLowerCase(c)) {
                allChars.append(c);
                lowercaseChars.append(c);
            } else if (!Character.isLetterOrDigit(c)) {
                allChars.append(c);
                specialChars.append(c);
            }
        }
        
        // Put values into "keys"
        // See explaination above structure
        result.put("all", allChars);
        result.put("upper", uppercaseChars);
        result.put("lower", lowercaseChars);
        result.put("special", specialChars);
        
        // See in 19950022400 - Section 5.6 for return statement
        return (result);
    }
}
