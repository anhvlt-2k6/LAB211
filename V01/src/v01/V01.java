package v01;

import java.util.Scanner;

public class V01 {
    
    private final String accountValidation = "[0-9]{14}";
    private final String pinValidation = "[0-9]{6}";
    
    private final String idCardValidation = "[0-9]{7}";
    private final String accountNameValidation = "[A-Za-z ]{1,}";
    private final String balancesValidation = "([0-9]{1,32})\\.([0-9]{1,32})";
    private final String moneyTypeValidation = "[A-Z]{3}";
    
    private final Scanner sc;
    
    private final ATMBackend atmbackend;
    
    public V01() {
        sc = new Scanner(System.in);
        atmbackend = new ATMBackend();
    }
    
    private void transferMoney() {
        try {
            boolean isTransferMoneySuccess = false;
            
            while (!isTransferMoneySuccess) {
                
                String sourceId = "";
                while (!sourceId.matches(idCardValidation)) {
                    System.out.print("Please enter an Source ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    if (idUserInput.matches(idCardValidation)) {
                        sourceId = idUserInput;
                    } else {
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                String targetId = "";
                while (!targetId.matches(idCardValidation)) {
                    System.out.print("Please enter an Target ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    if (idUserInput.matches(idCardValidation)) {
                        targetId = idUserInput;
                    } else {
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                String amountStr = "";
                while (!amountStr.matches(balancesValidation)) {
                    System.out.print("Please enter amount you want to transfer: ");
                    String balancesUserInput = sc.nextLine().trim();
                    
                    if (balancesUserInput.matches(balancesValidation)) {
                            amountStr = balancesUserInput;
                    } else {
                        System.out.println("An amount should be a float number.");
                    }
                }
                
                try {
                    double amount = Double.parseDouble(amountStr);
                    isTransferMoneySuccess = atmbackend.isTransferMoneySuccess(sourceId, targetId, amount);
                } catch (NumberFormatException numex) {
                    System.out.println("Amount should be a number!");
                }
                
                if (!isTransferMoneySuccess) {
                    System.out.println("Unable to transfer. Please check parameters");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
    
    private void withdrawalMoney() {
        try {
            
            boolean iswithdrawalMoneySuccess = false;
            
            while (!iswithdrawalMoneySuccess) {
                
                String id = "";
                while (!id.matches(idCardValidation)) {
                    System.out.print("Please enter an ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    if (idUserInput.matches(idCardValidation)) {
                        id = idUserInput;
                    } else {
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                String amountStr = "";
                while (!amountStr.matches(balancesValidation)) {
                    System.out.print("Please enter amount you want to withdrawal: ");
                    String balancesUserInput = sc.nextLine().trim();
                    
                    if (balancesUserInput.matches(balancesValidation)) {
                            amountStr = balancesUserInput;
                    } else {
                        System.out.println("An amount should be a float number.");
                    }
                }
                
                try {
                    double amount = Double.parseDouble(amountStr);
                    iswithdrawalMoneySuccess = atmbackend.iswithdrawalMoneySuccess(id, amount);
                } catch (NumberFormatException numex) {
                    System.out.println("Amount should be a number!");
                }
                
                if (!iswithdrawalMoneySuccess) {
                    System.out.println("Unable to withdrawal. Please check parameters");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
    
    private void registerAccount() {
        try {
            boolean isRegisterSuccess = false;
            
            while (!isRegisterSuccess) {
                
                String id = "";
                while (!id.matches(idCardValidation)) {
                    System.out.print("Please enter an ID: ");
                    String idUserInput = sc.nextLine().trim();
                    
                    if (idUserInput.matches(idCardValidation)) {
                        id = idUserInput;
                    } else {
                        System.out.println("An ID card must contain 7 digits.");
                    }
                }
                
                String accountName = "";
                while(!accountName.matches(accountNameValidation)) {
                    System.out.print("Please enter a name for this account: ");
                    String accountNameUserInput = sc.nextLine().trim();
                    
                    if (accountNameUserInput.matches(accountNameValidation)) {
                        accountName = accountNameUserInput;
                    } else {
                        System.out.print("A name should only contain a letters and spaces.");
                    }
                }
                
                String balances = "";
                while (!balances.matches(balancesValidation)) {
                    System.out.print("Please enter a balances: ");
                    String balancesUserInput = sc.nextLine().trim();
                    
                    if (balancesUserInput.matches(balancesValidation)) {
                        balances = balancesUserInput;
                    } else {
                        System.out.println("A balance should be a float number.");
                    }
                }
                
                String moneyType = "";
                while (moneyType.matches(moneyTypeValidation)) {
                    System.out.println("Please enter your money type (e.g USD, VND): ");
                    String moneyTypeUserInput = sc.nextLine().trim();
                    
                    if (moneyTypeUserInput.matches(moneyTypeValidation)) {
                        moneyType = moneyTypeUserInput;
                    } else {
                        System.out.println("Money type should be 3 uppercase letters.");
                    }
                }
                
                isRegisterSuccess = atmbackend.isRegisterSuccess(id, accountName, balances, moneyType);
                
                if (!isRegisterSuccess) {
                    System.out.println("Unable to register. Please check parameters");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
    
    private void loginUserInterface() {
        
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
            System.out.print(
                "==== ATM Login Portal ====\n" +
                "1. Register Account\n" +
                "2. Withdraw money\n" +
                "3. Transfer money\n" +
                "4. Exit\n" +
                "Please choose: "
            );
            
            int choice = Integer.parseInt(sc.nextLine());
            
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
