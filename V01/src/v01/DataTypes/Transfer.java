package v01.DataTypes;

/**
 * V01 - Transfer class as blueprint for data of transfers
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-28
 */
public class Transfer {
    
    // Properties of a transfer
    private final String fromId;
    private final String toId;
    private final String dateTime;
    private final double amount;
    private final boolean isSuccess;
    
    /**
     * Constructor of the Transfer
     * @param data as array of String. It should contain information as follow this index
     *      0 - fromID (or card id)
     *      1 - toID (or target card id)
     *      2 - datetime
     *      3 - amount of transfer
     *      4 - if the transfer is success
     * @throws Exception in case the array does not have exact 5 params or unable to parse number from string
     */
    public Transfer(String[] data) throws Exception {
        if (data.length == 5) {
            this.fromId = data[0];
            this.toId = data[1];
            this.dateTime = data[2];
            
            try {
                this.amount = Double.parseDouble(data[3]);
            } catch (NumberFormatException e) {
                // In case unable to parse number
                throw new Exception("Unable to parse for amount");
            }
            
            this.isSuccess = Boolean.parseBoolean(data[4]);
        } else {
            // in case the array does not have exact 5 params
            throw new ArrayIndexOutOfBoundsException("Transfer database requires 5 parameters!");
        }
    }

    /**
     * Get the from card id
     * @return string
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * Get the target card id
     * @return string
     */
    public String getToId() {
        return toId;
    }

    /**
     * Get the date time
     * @return string
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Get the amount
     * @return double
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Get whether the transfer is success or not
     * @return boolean
     */
    public boolean isIsSuccess() {
        return isSuccess;
    }
}
