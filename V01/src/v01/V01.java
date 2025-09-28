package v01;

import java.util.Scanner;

/**
 * V01 - Entry class as User interface handler
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-28
 */
public class V01 {
    
    /**
     * Default properties for V01
     * 
     * accountValidation and pinValidation - for validating accounts
     * 
     * idCardValidation, accountNameValidation, balancesValidation, moneyTypeValidation - for validating cards
     *
     * sc as user input
     * atmbackend as ATM Backend - handle data and files
     */
    
    private final String accountValidation = "[0-9]{14}";
    private final String pinValidation = "[0-9]{6}";
    
    private final String idCardValidation = "[0-9]{7}";
    private final String accountNameValidation = "[A-Za-z0-9 ]{1,}";
    private final String balancesValidation = "([0-9]{1,32})(\\.[0-9]{1,32})?";
    private final String moneyTypeValidation = "[A-Z]{3}";
    
    private final Scanner sc;
    
    private final ATMBackend atmbackend;
    
    /**
     * Constructor of V01
     * Initialize the user input and ATM Backend
     */
    public V01() {
        sc = new Scanner(System.in);
        atmbackend = new ATMBackend();
    }
    
    /**
     * Transfer Money User Interface and system handler
     */
    private void transferMoney() {
        try {
            // Assume that the trasnfer is failed
            boolean isTransferMoneySuccess = false;
            
            // Loop for user to re-enter information if the transfer continously is failed
            while (!isTransferMoneySuccess) {
                
                // Get and set for the source card id. assume first the soruce id is empty
                String sourceId = "";
                while (!sourceId.matches(idCardValidation)) {
                    // Ask user to enter source card id
                    System.out.print("Please enter an Source ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    // If user enter the card valid, set the soruceId to that value.
                    //  it also breaks the loop
                    if (idUserInput.matches(idCardValidation)) {
                        sourceId = idUserInput;
                    } else {
                        // Notify the user
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                // Get and set for the targer card id. assume first the target id is empty
                String targetId = "";
                while (!targetId.matches(idCardValidation)) {
                    // Ask user to enter target card id
                    System.out.print("Please enter an Target ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    // If user enter the card valid, set the targetId to that value.
                    //  it also breaks the loop
                    if (idUserInput.matches(idCardValidation)) {
                        targetId = idUserInput;
                    } else {
                        // Notify the user
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                // Get and set for the amount. assume first the amount is empty
                String amountStr = "";
                while (!amountStr.matches(balancesValidation)) {
                    // Ask user to enter amount
                    System.out.print("Please enter amount you want to transfer: ");
                    String balancesUserInput = sc.nextLine().trim();
                    
                    // If user enter the valid amount, set the amountStr to that value.
                    //  it also breaks the loop
                    if (balancesUserInput.matches(balancesValidation)) {
                            amountStr = balancesUserInput;
                    } else {
                        // Notify the user
                        System.out.println("An amount should be a float number.");
                    }
                }
                
                try {
                    // Try parse the amount
                    double amount = Double.parseDouble(amountStr);
                    
                    // Validation if the transfer is success
                    isTransferMoneySuccess = atmbackend.isTransferMoneySuccess(sourceId, targetId, amount);
                } catch (NumberFormatException numex) {
                    // In case unable to parse, notify users
                    System.out.println("Amount should be a number!");
                }
                
                // If the transfer is not success, ask user to verify input
                if (!isTransferMoneySuccess) {
                    System.out.println("Unable to transfer. Please check parameters");
                }
            }
        } catch (Exception e) {
            // Display for errors
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
    
     /**
     * Withdrawal Money User Interface and system handler
     */
    private void withdrawalMoney() {
        try {
            
            // Assume that the withdrawal is failed
            boolean iswithdrawalMoneySuccess = false;
            
            // Loop for user to re-enter information if the withdrawal continously is failed
            while (!iswithdrawalMoneySuccess) {
                
                // Get and set for the card id. assume first the id is empty
                String id = "";
                while (!id.matches(idCardValidation)) {
                    // Ask user to enter card id
                    System.out.print("Please enter an ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    // If user enter the card valid, set the id to that value.
                    //  it also breaks the loop
                    if (idUserInput.matches(idCardValidation)) {
                        id = idUserInput;
                    } else {
                        // Notify user
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                // Get and set for the amount. assume first the amount is empty
                String amountStr = "";
                while (!amountStr.matches(balancesValidation)) {
                    // Ask user to enter amount
                    System.out.print("Please enter amount you want to transfer: ");
                    String balancesUserInput = sc.nextLine().trim();
                    
                    // If user enter the valid amount, set the amountStr to that value.
                    //  it also breaks the loop
                    if (balancesUserInput.matches(balancesValidation)) {
                            amountStr = balancesUserInput;
                    } else {
                        // Notify the user
                        System.out.println("An amount should be a float number.");
                    }
                }
                
                try {
                    // Try parse the amount
                    double amount = Double.parseDouble(amountStr);
                    
                    // Validation if the transfer is success
                    iswithdrawalMoneySuccess = atmbackend.iswithdrawalMoneySuccess(id, amount);
                } catch (NumberFormatException numex) {
                    // In case unable to parse, notify users
                    System.out.println("Amount should be a number!");
                }
                
                // If the transfer is not success, ask user to verify input
                if (!iswithdrawalMoneySuccess) {
                    System.out.println("Unable to withdrawal. Please check parameters");
                }
            }
        } catch (Exception e) {
            // Display for errors
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
    
    /**
     * Register account User Interface and system handler
     */
    private void registerAccount() {
        try {
            // Assume that the withdrawal is failed
            boolean isRegisterSuccess = false;
            
            // Loop for user to re-enter information if the registration continously is failed
            while (!isRegisterSuccess) {
                
                // Get and set for the card id. assume first the id is empty
                String id = "";
                while (!id.matches(idCardValidation)) {
                    // Ask user to enter card id
                    System.out.print("Please enter an ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    // If user enter the card valid, set the id to that value.
                    //  it also breaks the loop
                    if (idUserInput.matches(idCardValidation)) {
                        id = idUserInput;
                    } else {
                        // Notify user
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                // Get and set for the card name. assume first the name is empty
                String accountName = "";
                while(!accountName.matches(accountNameValidation)) {
                    // Ask user to enter name
                    System.out.print("Please enter a name for this account: ");
                    String accountNameUserInput = sc.nextLine().trim();
                    
                    // If user enter the name valid, set the name to that value.
                    //  it also breaks the loop
                    if (accountNameUserInput.matches(accountNameValidation)) {
                        accountName = accountNameUserInput;
                    } else {
                        // Notify User
                        System.out.println("A name should only contain a letters and spaces.");
                    }
                }
                
                // Get and set for the card balance. assume first the balance is empty
                String balances = "";
                while (!balances.matches(balancesValidation)) {
                    // Ask user to enter amount
                    System.out.print("Please enter a balances: ");
                    String balancesUserInput = sc.nextLine().trim();
                    
                    // If user enter the balance valid, set the balance to that value.
                    //  it also breaks the loop
                    if (balancesUserInput.matches(balancesValidation)) {
                        balances = balancesUserInput;
                    } else {
                        // Notify User
                        System.out.println("A balance should be a float number.");
                    }
                }
                
                // Get and set for the money type. assume first the money type is empty
                String moneyType = "";
                while (!moneyType.matches(moneyTypeValidation)) {
                    // Ask user to enter money type
                    System.out.print("Please enter your money type (e.g USD, VND): ");
                    String moneyTypeUserInput = sc.nextLine().trim();
                    
                    // If user enter the money type valid, set the money type to that value.
                    //  it also breaks the loop
                    if (moneyTypeUserInput.matches(moneyTypeValidation)) {
                        moneyType = moneyTypeUserInput;
                    } else {
                        // Notify User
                        System.out.println("Money type should be 3 uppercase letters.");
                    }
                }

                // Validation if the transfer is success
                isRegisterSuccess = atmbackend.isRegisterSuccess(id, accountName, balances, moneyType);
                
                // If the transfer is not success, ask user to verify input
                if (!isRegisterSuccess) {
                    System.out.println("Unable to register. Please check parameters");
                }
            }
            
        } catch (Exception e) {
            // Display for errors
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
    
    private void loginUserInterface() {
        
        // Assume that the account and pin is empty
        String account = "";
        String pin = "";
        
        // assume that user not logged in
        boolean isLoggingSuccess = false;
        
        // Loop for user to re-enter information if the registration continously is failed
        while (!isLoggingSuccess) {
            
            // Get and set for the account. assume first account is empty
            String accountUserInput = "";
            while (!accountUserInput.matches(accountValidation)) {
                // Ask user to enter account
                System.out.print("Please enter the account: ");
                accountUserInput = sc.nextLine().trim();
                
                // If user enter the account valid, set the account to that value.
                //  it also breaks the loop
                if (accountUserInput.matches(accountValidation)) {
                    account = accountUserInput;
                } else {
                    // Notify User
                    System.out.println("Please re-enter the account. Must contain 14 digits only");
                }
            }
            
            // Get and set for the pin. assume first pin is empty
            String pinUserInput = "";
            while (!pinUserInput.matches(pinValidation)) {
                // Ask user to enter pin
                System.out.print("Please enter PIN: ");
                pinUserInput = sc.nextLine().trim();
                
                // If user enter the pin valid, set the pin to that value.
                //  it also breaks the loop
                if (pinUserInput.matches(pinValidation)) {
                    pin = pinUserInput;
                    break;
                } else {
                    // Notify User
                    System.out.println("Please re-enter the PIN. Must contain 6 digits only");
                }
            }
            
            // Validation if the login is success
            isLoggingSuccess = atmbackend.isLoginSuccess(account, pin);
            
            // If the login is not success, ask user to verify input
            if (!isLoggingSuccess) {
                System.out.println("Unable to login. Re-check your credential.");
            }
        }
        
        // Now print for user interface
        try {
            System.out.print(
                "==== ATM Login Portal ====\n" +
                "1. Register Account\n" +
                "2. Withdraw money\n" +
                "3. Transfer money\n" +
                "4. Exit\n" +
                "Please choose: "
            );
            
            // Try parse user choice
            int choice = Integer.parseInt(sc.nextLine());
            
            // For each case
            switch (choice) {
                case 1:
                    registerAccount();
                    break;
                case 2:
                    withdrawalMoney();
                    break;
                case 3:
                    transferMoney();
                    break;
                default:
                    // Fall back for invalid choice
                    System.out.println("Invalid choice");
                    break;
            }
        } catch (NumberFormatException numex) {
            // Invalid input, notify
            System.out.println("Invalid choice");
        }
    }
    
    /**
     * Entry of the program
     * @param args Useless
     */
    public static void main(String[] args) {
        V01 v01 = new V01(); // Initialize the object
        
        v01.loginUserInterface(); // Display the first UI
    }
}
