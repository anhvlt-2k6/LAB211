package v01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import v01.DataTypes.Account;
import v01.DataTypes.Card;
import v01.DataTypes.Transfer;
import v01.DataTypes.Withdrawal;

public final class ATMBackend {
    
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
    //  Read functions
    /////////////////////////////////////////////////////////////////////
    
    public void readInfo(String arrayListCase) {
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
                        cards.add(new Card(data));
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
    
    public void isAddedSuccess() throws Exception {
        
    }
    
    public boolean isLoginSuccess(String account, String pin) {
        boolean isLogged = false;
        
        for (Account a : accounts) {
            if (a.getAccount().equals(account) && a.getPin().equals(pin)) {
                isLogged = true;
                break;
            }
        }
        
        return (isLogged);
    }
}
