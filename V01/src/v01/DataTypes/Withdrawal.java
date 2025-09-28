package v01.DataTypes;

/**
 * V01 - Withdrawal class as blueprint for data of withdrawals
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-28
 */
public class Withdrawal {
    
    private final String id;
    private final String dateTime;
    private final double withdrawalAmount;
    private final boolean isSuccess;
    
    /** 
     * Constructor of Withdrawal
     * @param data as array of String. It should contain infor as follow this index
     *      0- as card id
     *      1 - as date time withdrawal happen
     *      2 - withdrawal amount
     *      3 - if the withdrawal is success
     * @throws Exception
     */
    public Withdrawal(String[] data) throws Exception {
        if (data.length == 4) {
            this.id = data[0];
            this.dateTime = data[1];
            try {
                this.withdrawalAmount = Double.parseDouble(data[2]);
            } catch (NumberFormatException e) {
                // In case unable to parse number
                throw new Exception("Unable to parse for amount");
            }
            
            this.isSuccess = Boolean.parseBoolean(data[3]);
        } else {
            // in case the array does not have exact 4 params
            throw new ArrayIndexOutOfBoundsException("Withdrawal database requires 4 parameters!");
        }
    }

    /**
     * Get the card id
     * @return string
     */ 
    public String getId() {
        return id;
    }

    /**
     * Get the date time
     * @return String
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Get withdrawal amount
     * @return string
     */
    public double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    /**
     * Get whether the withdrawal is success or not
     * @return boolean
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }
}
