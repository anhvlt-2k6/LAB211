package testapp;

public class TestApp {
    
    public static void main(String[] args) {
        int i = 3;
        switch (i) {
            case 1:
                System.out.println("1");
            case 2:
                System.out.println("2");
            case 3:
                System.out.println("3");
            default:
                System.out.println("Default");
                break;
        }
    }
}
