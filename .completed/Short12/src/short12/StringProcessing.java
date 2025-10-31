package short12;

import java.util.HashMap;

/**
 * Short 12 - String Processing Class
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public class StringProcessing {
    // Hash Map for converting binary into decimal
    private final HashMap<String, Double> binary = new HashMap<String, Double>() {
        private static final long serialVersionUID = 1L;
        {
            put("0", 0.0);
            put("1", 1.0);
        }
    };
 
    // Hash Map for converting hexadecimal into decimal
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
    
    // Hash Map for converting octal into decimal
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
    
    /**
     * Converting binary into Decimal
     * @param input string as input in binary
     * @return String as the result of converting bin into dec
     */
    public String binToDec(String input) {
        return (String.format("%.0f", convertOperation(input, binary, 2)));
    }
    
    /**
     * Converting hex into Decimal
     * @param input string as input in hex
     * @return String as the result of converting hex into dec
     */
    public String hexToDec(String input) {
        return (String.format("%.0f", convertOperation(input, hex, 16)));
    }
    
    /**
     * Converting octal into Decimal
     * @param input string as input in octal
     * @return String as the result of converting octal into dec
     */
    public String octToDec(String input) {
        return (String.format("%.0f", convertOperation(input, octal, 8)));
    }
    
    /**
     * Converting a X-ary to decimal result in double
     * @param input as String of the X-ary
     * @param map as map for convert that string into result
     * @param base as the base of X-ary
     * @return double of the result
     */
    private double convertOperation(String input, HashMap<String, Double> map, int base) {
        // Assume that result is 0
        double output = 0;
        
        // reverse the string array for little endian operation (easier to work with array)
        String[] reverseArr = this.reverseString(input).split("");
        
        // Loop for value and calculate the result
        // Result = result + (value of the array) * base ^ i (little endian)
        for (int i = 0; i < reverseArr.length; i++) {
            output += map.get(reverseArr[i]) * Math.pow(base, i);
        }
        
        // result the double
        return (output);
    }
    
    /**
     * Do reverse a string a return it (Little Endian Operation)
     * @param str as input string
     * @return String as reversed
     */
    private String reverseString(String str) {
        String strRet = "";
        
        // Loop for reverse the string
        for (String s : str.split("")) {
            strRet = s + strRet;
        }
        
        return (strRet);
    }
}
