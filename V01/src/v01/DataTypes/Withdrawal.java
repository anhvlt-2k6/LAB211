package v01.DataTypes;

public class Withdrawal {
    
    private final String id;
    private final String dateTime;
    private final double withdrawalAmount;
    private final boolean isSuccess;
    
    public Withdrawal(String[] data) throws Exception {
        if (data.length == 4) {
            this.id = data[0];
            this.dateTime = data[1];
            try {
                this.withdrawalAmount = Double.parseDouble(data[2]);
            } catch (NumberFormatException e) {
                throw new Exception("Unable to parse for amount");
            }
            
            this.isSuccess = Boolean.parseBoolean(data[3]);
        } else {
            throw new ArrayIndexOutOfBoundsException("Withdrawal database requires 4 parameters!");
        }
    }

    public String getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }
}
