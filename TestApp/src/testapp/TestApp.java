package testapp;

public class TestApp {
    
    public static void main(String[] args) {
        String passValidator = ".+";
        String pass = "1";
        System.out.println(pass.matches(passValidator));
    }
}
