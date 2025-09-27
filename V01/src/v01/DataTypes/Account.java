package v01.DataTypes;

/**
 * V01 - Account class as blueprint for data of accounts
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-28
 */
public final class Account {
    
    // account and pin of the account
    private final String account;
    private final String pin;

    /**
     * Constructor of Account
     * @param data as array of String. It should contain infor as follow this index
     *      0 - account
     *      1 - pin
     * @throws ArrayIndexOutOfBoundsException in case the array does not have exact 2 params
     */
    public Account(String[] data) throws ArrayIndexOutOfBoundsException {
        if (data.length == 2) {
            this.account = data[0];
            this.pin = data[1];
        } else {
            // in case the array does not have exact 2 params
            throw new ArrayIndexOutOfBoundsException("Account database requires 2 parameters!");
        }
    }
    
    /**
     * Get Account
     * @return string as Account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Get PIN
     * @return string as pin
     */
    public String getPin() {
        return pin;
    }
}
