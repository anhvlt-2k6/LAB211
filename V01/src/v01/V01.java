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
        
        while (!atmbackend.isLoginSuccess(account, pin)) {
            
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
            
            
            
        }
    }
    
    public static void main(String[] args) {
        V01 v01 = new V01();
        v01.loginUserInterface();
    }
}
