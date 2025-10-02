package short11;

/**
 * Short 11 - User Interface and Entry program
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class StringProcessor {
    
    // Original String and reversed one
    private String originalStr, reversedStr;
    
    /**
     * set the original string and reversed string 
     * @param userInput as user input string
     */
    public void setUserInput(String userInput) {
        this.originalStr = userInput;
        this.reversedStr = this.reverseString(userInput);
    }

    /**
     * Get the original String
     * @return String
     */
    public String getOriginalStr() {
        return originalStr;
    }

    /**
     * Get the reversed one
     * @return String
     */
    public String getReversedStr() {
        return reversedStr;
    }
    
    /**
     * Do reverse a string a return it
     * @param str as input string
     * @return String as reversed
     */
    private String reverseString(String str) {
        String strRet = "";
        
        for (String s : str.split(" ")) {
            strRet = s + " " + strRet;
        }
        
        return (strRet);
    } 
}
