package v01.DataTypes;

public final class Account {
    
    private final String account;
    private final String pin;

    public Account(String[] data) throws ArrayIndexOutOfBoundsException {
        if (data.length == 2) {
            this.account = data[0];
            this.pin = data[1];
        } else {
            throw new ArrayIndexOutOfBoundsException("Account database requires 2 parameters!");
        }
    }
    
    public String getAccount() {
        return account;
    }

    public String getPin() {
        return pin;
    }
}
