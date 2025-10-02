package testapp;

public class TestApp {
    
    private static String reverseString(String str) {
        String strRet = "";
        
        for (String s : str.split("")) {
            strRet = s + strRet;
        }
        
        return (strRet);
    }
    
    public static void main(String[] args) {
        String rev = reverseString("abc");
        System.out.println(rev);
    }
}
