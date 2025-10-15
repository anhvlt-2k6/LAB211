package testapp;

import java.util.ArrayList;

public class TestApp {
    
    public static int getFibbonaci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        
        return getFibbonaci(n - 1) + getFibbonaci(n - 2);
    }
    
    public static void main(String[] args) {
        System.out.println(getFibbonaci(0));
    }
}
