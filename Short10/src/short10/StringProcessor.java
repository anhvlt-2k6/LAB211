package short10;

/**
 * Short 10 - String Processor
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class StringProcessor {
    
    // Char Map and User Input char array
    private int[] charMap;
    private char[] userInput;
    
    /**
     * Constructor of String Processor
     */
    public StringProcessor() {
        this.resetCharMap();
    }

    /**
     * Set the user input char array
     * @param userInput as String. Will be converted into array of char
     */
    public void setUserInput(String userInput) {
        this.resetCharMap();
        this.userInput = userInput.toCharArray();
        this.processString();
    }
    
    /**
     * Get the char map
     * @return int[] as Char Map
     */
    public int[] getCharMap() {
        return charMap;
    }

    /**
     * Reset charMap
     */
    private void resetCharMap() {
        charMap = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    /**
     * Processing the String, set value into char map
     */
    private void processString() {
        // Loop through the character array
        for (char c : userInput) {
            // If the target is char, proceeding next
            if (isAlpha(c)) {
                if (isUpper(c)) {
                    // If the upper, add counting
                    charMap[c - 'A'] += 1;
                } else if (isLower(c)) {
                    // If the lower, add counting
                    charMap[c - 'a'] += 1;
                }
            }
        }
    }
    
    /**
     * Check if the character is letter
     * @param c as char but in int
     * @return is if the character is letter
     */
    private boolean isAlpha(int c) {
        return (isUpper(c) || isLower(c));
    }
    
    /**
     * Check if the character is upper
     * @param c as char but in int
     * @return is if the character is upper
     */
    private boolean isUpper(int c) {
        return (c >= 'A' && c <= 'Z');
    }
    
    /**
     * Check if the character is lower
     * @param c as char but in int
     * @return is if the character is lower
     */
    private boolean isLower(int c) {
        return (c >= 'a' && c <= 'z');
    }
}
