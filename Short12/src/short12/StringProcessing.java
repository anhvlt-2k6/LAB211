package short12;

import java.util.HashMap;

public class StringProcessing {
    
    private final HashMap<String, Double> binary = new HashMap<String, Double>() {
        private static final long serialVersionUID = 1L;
        {
            put("0", 0.0);
            put("1", 1.0);
        }
    };
 
    private final HashMap<String, Double> hex = new HashMap<String, Double>() {
        private static final long serialVersionUID = 1L;
        {
            put("0", 0.0);
            put("1", 1.0);
            put("2", 2.0);
            put("3", 3.0);
            put("4", 4.0);
            put("5", 5.0);
            put("6", 6.0);
            put("7", 7.0);
            put("8", 8.0);
            put("9", 9.0);
            put("A", 10.0);
            put("B", 11.0);
            put("C", 12.0);
            put("D", 13.0);
            put("E", 14.0);
            put("F", 15.0);
            put("a", 10.0);
            put("b", 11.0);
            put("c", 12.0);
            put("d", 13.0);
            put("e", 14.0);
            put("f", 15.0);
        }
    };
    
    private final HashMap<String, Double> octal = new HashMap<String, Double>() {
        private static final long serialVersionUID = 1L;
        {
            put("0", 0.0);
            put("1", 1.0);
            put("2", 2.0);
            put("3", 3.0);
            put("4", 4.0);
            put("5", 5.0);
            put("6", 6.0);
            put("7", 7.0);
        }
    };
    
    public String binToDec(String input) {
        double output = 0;
        String[] reverseArr = this.reverseString(input).split("");
        
        for (int i = 0; i < reverseArr.length; i++) {
            output += binary.get(reverseArr[i]) * Math.pow(2, i);
        }
        
        return (String.format("%.0f", output));
    }
    
    public String hexToDec(String input) {
        double output = 0;
        String[] reverseArr = this.reverseString(input).split("");
        
        for (int i = 0; i < reverseArr.length; i++) {
            output += hex.get(reverseArr[i]) * Math.pow(16, i);
        }
        
        return (String.format("%.0f", output));
    }
    
    public String octToDec(String input) {
        double output = 0;
        String[] reverseArr = this.reverseString(input).split("");
        
        for (int i = 0; i < reverseArr.length; i++) {
            output += octal.get(reverseArr[i]) * Math.pow(8, i);
        }
        
        return (String.format("%.0f", output));
    }
    
    /**
     * Do reverse a string a return it (Little Endian Operation)
     * @param str as input string
     * @return String as reversed
     */
    private String reverseString(String str) {
        String strRet = "";
        
        for (String s : str.split("")) {
            strRet = s + strRet;
        }
        
        return (strRet);
    } 
}
