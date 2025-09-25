package v01.DataTypes;

public final class Card {
    
    private final String account;
    private final String id;
    private final String accountName;
    private double balances;
    private final String moneyType;
    
    public Card(String[] data) throws ArrayIndexOutOfBoundsException {
        if (data.length == 4) {
            this.account = data[0];
            this.id = data[1];
            this.accountName = data[2];
            this.balances = 0;
            this.moneyType = data[3];
        } else {
            throw new ArrayIndexOutOfBoundsException("Card database requires 4 parameters!");
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
