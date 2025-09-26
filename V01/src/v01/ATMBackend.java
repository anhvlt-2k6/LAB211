package v01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import v01.DataTypes.Account;
import v01.DataTypes.Card;
import v01.DataTypes.Transfer;
import v01.DataTypes.Withdrawal;

public final class ATMBackend {
    
    private Account currentAccount;
    
    private final ArrayList<Account> accounts;
    private final ArrayList<Card> cards;
    private final ArrayList<Withdrawal> withdrawals;
    private final ArrayList<Transfer> transfers;
    
    private final String cardFn = "cards.txt";
    private final String accountFn = "accounts.txt";
    private final String withdrawalFn = "withdrawals.txt";
    private final String transferFn = "transfers.txt";
    
    private Scanner fileReader;
    private FileWriter fileWriter;
    
    public ATMBackend() {
        this.accounts = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.withdrawals = new ArrayList<>();
        this.transfers = new ArrayList<>();
        
        this.readInfo("account");
        this.readInfo("card");
        this.readInfo("transfer");
        this.readInfo("withdrawal");
    }
    
    /////////////////////////////////////////////////////////////////////
    //  Non-user-interface functions
    /////////////////////////////////////////////////////////////////////
    
    private void readInfo(String arrayListCase) {
        try {
            
            String fileName = "";
            
            switch (arrayListCase) {
                    case "account":
                        fileName = accountFn;
                        break;
                    case "card":
                        fileName = cardFn;
                        break;
                    case "transfer":
                        fileName = transferFn;
                        break;
                    case "withdrawal":
                        fileName = withdrawalFn;
                        break;
                    default:
                        break;
            }
            
            // Initialize the database file object
            File fw = new File(fileName);
            
            // Check if the file can be read/write. If unable, report to user.
            if (!fw.exists() || !fw.canRead()) {
                throw new FileNotFoundException(""); // Stop right here
            }
            
            fileReader = new Scanner(fw);
            
            while (fileReader.hasNext()) {
                String[] data = fileReader.nextLine().split(",");
                
                switch (arrayListCase) {
                    case "account":
                        accounts.add(new Account(data));
                        break;
                    case "card":
                        try {
                            cards.add(new Card(data));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getLocalizedMessage());
                        }
                        
                        break;
                    case "transfer":
                        try {
                            transfers.add(new Transfer(data));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getLocalizedMessage());
                        }
                        
                        break;
                    case "withdrawal":
                        try {
                            withdrawals.add(new Withdrawal(data));
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getLocalizedMessage());
                        }
                        
                        break;
                    default:
                        break;
                }
            }
        }
        catch (FileNotFoundException file_ex) {
            // Exception on file not found
            System.out.println("Unable to read file. No data.");
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException arr_ex) {
            // Exception on out of bound of the array.
        }
    }
    
    private void writeInfo(String arrayListCase) {
        try {
            String fileName = "", outStr = "";
            
            switch (arrayListCase) {
                    case "account":
                        fileName = accountFn;
                        break;
                    case "card":
                        fileName = cardFn;
                        break;
                    case "transfer":
                        fileName = transferFn;
                        break;
                    case "withdrawal":
                        fileName = withdrawalFn;
                        break;
                    default:
                        break;
            }
            
            // Now initialize the studentDbWriter.
            fileWriter = new FileWriter(fileName);
            
            // Just a loop through the ArrayList
            
            switch (arrayListCase) {
                    case "account":
                        for (Account a : accounts) {
                            outStr += String.format(
                                    "\n%s,%s",
                                    a.getAccount().trim(),
                                    a.getPin().trim()
                            );
                        }
                        break;
                    case "card":
                        for (Card c : cards) {
                            outStr += String.format(
                                    "\n%s,%s,%s,%f,%s",
                                    c.getAccount().trim(),
                                    c.getId().trim(),
                                    c.getAccountName().trim(),
                                    c.getBalances(),
                                    c.getMoneyType().trim()
                            );
                        }
                        break;
                    case "transfer":
                        for (Transfer t : transfers) {
                            outStr += String.format(
                                    "\n%s,%s,%s,%f,%s",
                                    t.getFromId(),
                                    t.getToId(),
                                    t.getDateTime(),
                                    t.getAmount(),
                                    (t.isIsSuccess() ? "true" : "false")
                            );
                        }
                        break;
                    case "withdrawal":
                        for (Withdrawal w : withdrawals) {
                            outStr += String.format(
                                    "\n%s,%s,%f,%s",
                                    w.getId(),
                                    w.getDateTime(),
                                    w.getWithdrawalAmount(),
                                    (w.isIsSuccess() ? "true" : "false")
                            );
                        }
                        break;
                    default:
                        break;
            }
            
            // Write to file and close
            fileWriter.write(outStr);
            fileWriter.close();
        }
        catch (FileNotFoundException ex_fnf) {
            // Exception will handle when file not found
            // The program will ignore and create new file if there is new input anyways
        }
        catch (IOException io_e) {
            // Exception will handle when IO Error with file
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    //  User-interface-based functions
    /////////////////////////////////////////////////////////////////////
    
    public boolean isLoginSuccess(String account, String pin) {
        boolean isLogged = false;
        
        for (Account a : accounts) {
            if (a.getAccount().equals(account) && a.getPin().equals(pin)) {
                currentAccount = a;
                isLogged = true;
                break;
            }
        }
        
        return (isLogged);
    }
    
    public boolean isRegisterSuccess(String id, String accountName, String balances, String moneyType) throws Exception {
        boolean isRegisterSuccessed = false;
        
        if (!(currentAccount == null)) {
            String[] data = {currentAccount.getAccount(), id, accountName, balances, moneyType};
            cards.add(new Card(data));
            this.writeInfo("card");
            isRegisterSuccessed = true;
        } else {
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        return (isRegisterSuccessed);
    }
}
