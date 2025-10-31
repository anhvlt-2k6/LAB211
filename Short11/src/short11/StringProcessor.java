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
        
        for (String str_no_space : str.split(" ")) {
            String str_no_space_ret = "";
            
            for (String str_no_underscore : str_no_space.split("_")) {
                str_no_space_ret = str_no_underscore + "_" + str_no_space_ret;
            }
            
            strRet = str_no_space_ret.substring(0, str_no_space_ret.length() - 1) + " " + strRet;
        }
        
        return (strRet.substring(0, strRet.length() - 1));
    }
}
