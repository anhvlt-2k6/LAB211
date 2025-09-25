package v01;

import java.util.Scanner;

public class V01 {
    
    private final String accountValidation = "[0-9]{14}";
    private final String pinValidation = "[0-9]{6}";
    
    private final Scanner sc;
    
    private final ATMBackend atmbackend;
    
    public V01() {
        sc = new Scanner(System.in);
        atmbackend = new ATMBackend();
    }
    
    private void loginUserInterface() {
        
        // Assume that the account and pin is empty, waiting for
        String account = "";
        String pin = "";
        
        boolean isLoggingSuccess = false;
        
        while (!isLoggingSuccess) {
            
            String accountUserInput = "";
            while (!accountUserInput.matches(accountValidation)) {
                System.out.print("Please enter the account: ");
                accountUserInput = sc.nextLine().trim();
                
                if (accountUserInput.matches(accountValidation)) {
                    account = accountUserInput;
                    break;
                } else {
                    System.out.println("Please re-enter the account. Must contain 14 digits only");
                }
            }
            
            String pinUserInput = "";
            while (!pinUserInput.matches(pinValidation)) {
                System.out.print("Please enter PIN: ");
                pinUserInput = sc.nextLine().trim();
                
                if (pinUserInput.matches(pinValidation)) {
                    pin = pinUserInput;
                    break;
                } else {
                    System.out.println("Please re-enter the PIN. Must contain 6 digits only");
                }
            }
            
            isLoggingSuccess = atmbackend.isLoginSuccess(account, pin);
            if (!isLoggingSuccess) {
                System.out.println("Unable to login. Re-check your credential.");
            }
        }
        
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } catch (NumberFormatException numex) {
            System.out.println("Invalid choice");
        }
    }
    
    public static void main(String[] args) {
        V01 v01 = new V01();
        v01.loginUserInterface();
    }
}
