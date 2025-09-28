package v01.DataTypes;

/**
 * V01 - Card class as blueprint for data of accounts
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-28
 */
public final class Card {
    
    private final String account;
    private final String id;
    private final String accountName;
    private double balances;
    private final String moneyType;
    
    /**
     *
     * @param data
     * @throws Exception
     */
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

    /**
     *
     * @return
     */
    public double getBalances() {
        return balances;
    }

    /**
     *
     * @param balances
     */
    public void setBalances(double balances) {
        this.balances = balances;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     *
     * @return
     */
    public String getMoneyType() {
        return moneyType;
    }

    /**
     *
     * @return
     */
    public String getAccount() {
        return account;
    }
}
