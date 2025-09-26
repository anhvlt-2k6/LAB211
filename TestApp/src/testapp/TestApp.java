package testapp;

import java.util.PriorityQueue;

public class TestApp {
    
    public static void main(String[] args) {
        String validation = "([0-9]{1,})\\.([0-9]{1,})";
        String input = "10.1.1.2.";
        
        System.out.println(input.matches(validation));
    }
}
