package v01.DataTypes;

public final class Card {
    
    private final String account;
    private final String id;
    private final String accountName;
    private double balances;
    private final String moneyType;
    
    public Card(String[] data) throws Exception {
        if (data.length == 5) {
            this.account = data[0];
            this.id = data[1];
            this.accountName = data[2];
            
            try {
                this.balances = Double.parseDouble(data[3]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Unable to parse for amount");
            }
            
            this.moneyType = data[4];
        } else {
            throw new ArrayIndexOutOfBoundsException("Card database requires 5 parameters!");
        }
    }

    public double getBalances() {
        return balances;
    }

    public void setBalances(double balances) {
        this.balances = balances;
    }

    public String getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public String getAccount() {
        return account;
    }
}
