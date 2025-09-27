package v01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
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
                
                if (data.length < 2) {
                    continue;
                }
                
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
        catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NullPointerException file_ex) {
            // Exception on file not found, out of bound (array), or null on object
        }
        finally {
            fileReader.close();
        }
    }
    
    private void writeInfo(String arrayListCase) {
        try {
            String fileName = "", outStr = "";
            
            switch (arrayListCase) {
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
    
    private boolean isCardIdExisted(String cardId) {
        boolean isCardExisted = false;
        
        for (Card c : cards) {
            if (c.getId().equals(cardId)) {
                isCardExisted = true;
                break;
            }
        }
        
        return (isCardExisted);
    }
    
    private void writeInformation() {
        this.writeInfo("card");
        this.writeInfo("transfer");
        this.writeInfo("withdrawal");
    }
    
    /////////////////////////////////////////////////////////////////////
    //  User-interface-based functions
    /////////////////////////////////////////////////////////////////////
    
    public boolean isTransferMoneySuccess(String sourceCardId, String targetCardId, double amount) throws Exception {
        boolean isTransferMoneySuccess = false;
        
        if (sourceCardId.equals(targetCardId)) {
            throw new Exception("Cannot trasfer to the same card.");
        }
        
        if (!(currentAccount == null)) {
            
            if (!isCardIdExisted(sourceCardId)) {
                throw new Exception("Source Card ID is not available");
            }
            
            if (!isCardIdExisted(targetCardId)) {
                throw new Exception("Target Card ID is not available");
            }
            
            Card source = null, destination = null;
            
            for (Card c : cards) {
                if (c.getId().equals(sourceCardId)) {
                    source = c;
                }
                
                if (c.getId().equals(targetCardId)) {
                    destination = c;
                }
            }
            
            
            if (source == null || destination == null) {
                throw new Exception("Either source or destination is not on the application database.");
            }
            
            if (!currentAccount.getAccount().equals(source.getAccount())) {
                throw new Exception("The source card Id is not own by user.");
            }
            
            if (source.getBalances() < amount) {
                throw new Exception("Current balance of source card id is not enough.");
            }
            
            source.setBalances(source.getBalances() - amount);
            destination.setBalances(destination.getBalances() + amount);
            
            isTransferMoneySuccess = true;
        } else {
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        String[] data = {
            sourceCardId,
            targetCardId,
            Instant.now().toString(),
            String.format("%f", amount),
            (isTransferMoneySuccess) ? "true" : "false"
        };
        
        transfers.add(new Transfer(data));
        
        this.writeInformation();
        
        return (isTransferMoneySuccess);
    }
    
    public boolean iswithdrawalMoneySuccess(String cardId, double amount) throws Exception {
        boolean iswithdrawalMoneySuccess = false;
        
        if (!(currentAccount == null)) {
            
            if (!isCardIdExisted(cardId)) {
                throw new Exception("Target card id does not exist!");
            }
            
            for (Card c : cards) {
                if (c.getId().equals(cardId)) {
                    
                    if (!currentAccount.getAccount().equals(c.getAccount())) {
                        throw new Exception("The source card Id is not own by user.");
                    }
                    
                    double currentBalance = c.getBalances();
                    
                    if (currentBalance >= amount) {
                        c.setBalances(currentBalance - amount);
                    } else {
                       throw new Exception("Current balance is lower than the amount you want to withdrawal!"); 
                    }
                    
                    iswithdrawalMoneySuccess = true;
                    
                    break;
                }
            }
        } else {
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        String[] data = {
            cardId,
            Instant.now().toString(),
            String.format("%f", amount),
            (iswithdrawalMoneySuccess) ? "true" : "false"
        };
        
        withdrawals.add(new Withdrawal(data));
        
        this.writeInformation();
        
        return (iswithdrawalMoneySuccess);
    }
    
    public boolean isRegisterSuccess(String id, String accountName, String balances, String moneyType) throws Exception {
        boolean isRegisterSuccessed = false;
        
        if (!(currentAccount == null)) {
            if (isCardIdExisted(id)) {
                throw new Exception("This card is already existed");
            }
            
            String[] data = {currentAccount.getAccount(), id, accountName, balances, moneyType};
            cards.add(new Card(data));

            this.writeInformation();
            
            isRegisterSuccessed = true;
        } else {
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        return (isRegisterSuccessed);
    }
    
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
}
