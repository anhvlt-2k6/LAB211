package short05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalysisString {
    
    public HashMap<String, ArrayList<Integer>> getNumber(String input) {
        // Return param
        HashMap<String, ArrayList<Integer>> result = new HashMap<>();
        
        // Anaysis values
        ArrayList<Integer> allNumbers = new ArrayList<>();
        ArrayList<Integer> squareNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        
        // Add all integer into a list (further analysis)
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            try {
                int val = Integer.parseInt(matcher.group());
                allNumbers.add(val);

                // 
                if (val >= 0) {
                    int r = (int) Math.sqrt(val);
                    if (r * r == val) {
                        squareNumbers.add(val);
                    }
                }

                // 
                if (val % 2 == 0) {
                    evenNumbers.add(val);
                } else {
                    oddNumbers.add(val);
                }
            } catch (NumberFormatException ex) {
                //
            }
        }

    result.put("all", allNumbers);
    result.put("square", squareNumbers);
    result.put("odd", oddNumbers);
    result.put("even", evenNumbers);

        
        return result;
    }
    
    public HashMap<String, StringBuilder> getCharacter(String input) {
        HashMap<String, StringBuilder> result = new HashMap<>();
        
        // Analysis values
        StringBuilder allChars = new StringBuilder();
        StringBuilder uppercaseChars = new StringBuilder();
        StringBuilder lowercaseChars = new StringBuilder();
        StringBuilder specialChars = new StringBuilder();
        
        for (int idx = 0; idx < input.length(); idx++) {
            char c = input.charAt(idx);
        
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
        
        result.put("all", allChars);
        result.put("upper", uppercaseChars);
        result.put("lower", lowercaseChars);
        result.put("special", specialChars);
        
        return result;
    }
}
