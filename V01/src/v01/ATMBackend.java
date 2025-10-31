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

/**
 * V01 - ATM Backend (act as the ATM file and data handler)
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-28
 */
public final class ATMBackend {
    
    /*
    currentAccount - act as a current logged in account (for user validation)
    accounts, cards, withdrawals, transfers - as data container for storing data in types
    cardFn, accountFn, withdrawalFn, transferFn - act as a target file
    
    fileReader, fileWriter - IO file handler to read and write data
    */
    
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
    
    /**
     * Constructor of ATM Backend
     */
    public ATMBackend() {
        // Initialize the container of data
        this.accounts = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.withdrawals = new ArrayList<>();
        this.transfers = new ArrayList<>();
        
        // start reading data by type
        this.readInfo("account");
        this.readInfo("card");
        this.readInfo("transfer");
        this.readInfo("withdrawal");
    }
    
    /////////////////////////////////////////////////////////////////////
    //  Non-user-interface functions
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Read info from target files (has postfix "Fn" of the variable)
     * @param arrayListCase as type. "account" for accounts, "card" for cards, "transfer" for transfer, "withdrawal" for withdrawal
     */
    private void readInfo(String arrayListCase) {
        try {
            
            // Initialize fileName as empty, will assign later
            String fileName = "";
            
            // for each case, fileName will be assigned with proper file name
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
            
            // Now initialize the file scanner
            fileReader = new Scanner(fw);
            
            // Loop through the file (as now the file reader)
            while (fileReader.hasNext()) {
                
                // Split file into strings
                String[] data = fileReader.nextLine().split(",");
                
                // Since all the target data type of the ATM has AT LEAST 2 params,
                //  if the length is less than 2, it should be ignored
                if (data.length < 2) {
                    continue;
                }
                
                // for each data, pass the data as array into the data containers
                switch (arrayListCase) {
                    case "account":
                        accounts.add(new Account(data)); // Pass data into account
                        break;
                    case "card":
                        try {
                            cards.add(new Card(data)); // Pass data into cards
                        } catch (Exception e) {
                            // In case unable to parse number or array out of bound, display
                            //  the exception and ignore
                            System.out.println("Error: " + e.getLocalizedMessage());
                        }
                        
                        break;
                    case "transfer":
                        try {
                            transfers.add(new Transfer(data)); // Pass daat into transfer
                        } catch (Exception e) {
                            // In case unable to parse number or array out of bound, display
                            //  the exception and ignore
                            System.out.println("Error: " + e.getLocalizedMessage());
                        }
                        
                        break;
                    case "withdrawal":
                        try {
                            withdrawals.add(new Withdrawal(data)); // Pass data into withdrawals
                        } catch (Exception e) {
                            // In case unable to parse number or array out of bound, display
                            //  the exception and ignore
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
    
    /**
     * Write info into target files (has postfix "Fn" of the variable)
     * @param arrayListCase as type. "account" for accounts, "card" for cards, "transfer" for transfer, "withdrawal" for withdrawal
     */
    private void writeInfo(String arrayListCase) {
        try {
            // Initialize fileName and outStr as empty, will assign later
            String fileName = "", outStr = "";
            
            // for each case, fileName will be assigned with proper file name
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
            
            // Just a loop through the data container to add
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
    
    /**
     * Check whether the card is existed
     * @param cardId as Card ID (String, not the account)
     * @return (boolean) if the card exists
     */
    private boolean isCardIdExisted(String cardId) {
        // Initialize the return value
        boolean isCardExisted = false;
        
        // Loops through the card data container, if found, set return value to
        //  true and exit the loop
        for (Card c : cards) {
            if (c.getId().equals(cardId)) {
                isCardExisted = true;
                break;
            }
        }
        
        // Return if the card exists
        return (isCardExisted);
    }
    
    /**
     * Write the information back to the files
     */
    private void writeInformation() {
        this.writeInfo("card");
        this.writeInfo("transfer");
        this.writeInfo("withdrawal");
    }
    
    /////////////////////////////////////////////////////////////////////
    //  User-interface-based functions
    /////////////////////////////////////////////////////////////////////

    /**
     * Do-and-verify if the transfer is success. Note the user must be signed-in
     * @param sourceCardId as the source card id. Note that the source card has an account as same as currentAccount.getAccount() and must not empty
     * @param targetCardId as the target Id. Note that target Id must be different than the source (but the same account id is accepted) and must not empty
     * @param amount as the amount user want to transfer
     * @return whether the return is success or not
     * @throws Exception if any of parameter does not meet any of those requirements above
     */
    
    public boolean isTransferMoneySuccess(String sourceCardId, String targetCardId, double amount) throws Exception {
        // Initialize the return value
        boolean isTransferMoneySuccess = false;
        
        // Throw exception in case source == target
        if (sourceCardId.equals(targetCardId)) {
            throw new Exception("Cannot trasfer to the same card.");
        }
        
        // Always check if user is signed-in
        if (!(currentAccount == null)) {
            
            // If the source card is not on the data container, throw exception
            if (!isCardIdExisted(sourceCardId)) {
                throw new Exception("Source Card ID is not available");
            }
            
            // If the target card is not on the data container, throw exception
            if (!isCardIdExisted(targetCardId)) {
                throw new Exception("Target Card ID is not available");
            }
            
            // Attempt if the source and destination card
            Card source = null, destination = null;
            
            // Get the source and destination card
            for (Card c : cards) {
                if (c.getId().equals(sourceCardId)) {
                    source = c;
                }
                
                if (c.getId().equals(targetCardId)) {
                    destination = c;
                }
            }
            
            // Double check, for if any of the card is not available
            if (source == null || destination == null) {
                throw new Exception("Either source or destination is not on the application database.");
            }
            
            // If the sourcec card is not owned by current account, throw exception
            if (!currentAccount.getAccount().equals(source.getAccount())) {
                throw new Exception("The source card Id is not own by user.");
            }
            
            // If the current balance < amount on the soruce, throw exception
            if (source.getBalances() < amount) {
                throw new Exception("Current balance of source card id is not enough.");
            }
            
            // Set balanace for both cards
            source.setBalances(source.getBalances() - amount);
            destination.setBalances(destination.getBalances() + amount);
            
            // set the return value as true
            isTransferMoneySuccess = true;
        } else {
            // Throw exception user not logged in
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        // now logging for this transfer
        String[] data = {
            sourceCardId,
            targetCardId,
            Instant.now().toString(),
            String.format("%f", amount),
            (isTransferMoneySuccess) ? "true" : "false"
        };
        
        // add data into transfer data container
        transfers.add(new Transfer(data));
        
        // write information
        this.writeInformation();
        
        // return value back if the transfer is success
        return (isTransferMoneySuccess);
    }
    
    /**
     * Do-and-verify if the withdrawal is success. Note the user must be signed-in
     * @param cardId as the card id. Note: must not empty
     * @param amount as the amount user want to withdrawal
     * @return whether the withdrawal is success or not
     * @throws Exception if any of parameter does not meet any of those requirements above
     */
    public boolean iswithdrawalMoneySuccess(String cardId, double amount) throws Exception {
        // Initialize the return value
        boolean iswithdrawalMoneySuccess = false;
        
        // Always check if user is signed-in
        if (!(currentAccount == null)) {
            
            // If the source card is not on the data container, throw exception
            if (!isCardIdExisted(cardId)) {
                throw new Exception("Target card id does not exist!");
            }
            
            // Loop through the card data container
            for (Card c : cards) {
                
                // Found a card that matches the param
                if (c.getId().equals(cardId)) {
                    
                    // If the sourcec card is not owned by current account, throw exception
                    if (!currentAccount.getAccount().equals(c.getAccount())) {
                        throw new Exception("The source card Id is not own by user.");
                    }
                    
                    // get the card balance
                    double currentBalance = c.getBalances();
                    
                    // if the balance is insufficant, throw exception, otherwise do the substract and return money
                    if (currentBalance >= amount) {
                        c.setBalances(currentBalance - amount);
                    } else {
                       throw new Exception("Current balance is lower than the amount you want to withdrawal!"); 
                    }
                    
                    // set the return value as true
                    iswithdrawalMoneySuccess = true;
                    
                    // once found, exit the loop to not wasting computational power
                    break;
                }
            }
        } else {
            // Throw exception user not logged in
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        // now logging for this withdrawal
        String[] data = {
            cardId,
            Instant.now().toString(),
            String.format("%f", amount),
            (iswithdrawalMoneySuccess) ? "true" : "false"
        };
        
        // add data into the container
        withdrawals.add(new Withdrawal(data));
        
        // write information
        this.writeInformation();
        
        // return value back
        return (iswithdrawalMoneySuccess);
    }
    
    /**
     * Do-and-verify if the registration new card is success. Note the user must be signed-in
     * @param id as card ID
     * @param accountName as card name (not user name)
     * @param balances as balances user want to enter
     * @param moneyType as money type
     * @return whether the return is success or not
     * @throws Exception if any of parameter does not meet any of those requirements above
     */
    public boolean isRegisterSuccess(String id, String accountName, String balances, String moneyType) throws Exception {
        // Initialize the return value
        boolean isRegisterSuccessed = false;
        
        // Always check if user is signed-in
        if (!(currentAccount == null)) {
            
            // Check if the card is already existed
            if (isCardIdExisted(id)) {
                throw new Exception("This card is already existed");
            }
            
            // Prepare the data param
            // See in the Card.java for params
            String[] data = {currentAccount.getAccount(), id, accountName, balances, moneyType};
            
            // add new data into the data container
            cards.add(new Card(data));

            // Write information to files
            this.writeInformation();
            
            // set the return value as true
            isRegisterSuccessed = true;
        } else {
            // Throw exception user not logged in
            throw new Exception("User is either not logged in or does not exist in the database.");
        }
        
        // return value back
        return (isRegisterSuccessed);
    }
    
    /**
     * Do-and-validate if the logging is succeed
     * @param account as the account
     * @param pin as pin
     * @return if user is found with & params
     */
    public boolean isLoginSuccess(String account, String pin) {
        // Initialize the return value
        boolean isLogged = false;
        
        // Loop for account, if found, 
        // set the currentAccount to that account, set return value to true and exit
        for (Account a : accounts) {
            if (a.getAccount().equals(account) && a.getPin().equals(pin)) {
                currentAccount = a;
                isLogged = true;
                break;
            }
        }
        
        // return value back
        return (isLogged);
    }
}
