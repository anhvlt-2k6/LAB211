package v01.DataTypes;

/**
 * V01 - Card class as blueprint for data of cards
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
     * Constructor of Card
     * @param data as array of String. It should contain infor as follow this index
     *      0 - account
     *      1 - id
     *      2 - account Name
     *      3 - balances
     *      4 - moneyType
     * @throws Exception in case the array does not have exact 5 params or unable to parse number from string
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
     * Get the current balance of card
     * @return double as balance
     */
    public double getBalances() {
        return balances;
    }

    /**
     * Set the balance
     * @param balances as the wanted balance
     */
    public void setBalances(double balances) {
        this.balances = balances;
    }

    /**
     * Get the card id
     * @return String as card id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the account name
     * @return String as account name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Get the card money type
     * @return String as money type
     */
    public String getMoneyType() {
        return moneyType;
    }

    /**
     * Get account that owned this card
     * @return String as owner id (or account)
     */
    public String getAccount() {
        return account;
    }
}
