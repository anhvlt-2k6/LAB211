package v01.DataTypes;

public class Transfer {
    
    private final String fromId;
    private final String toId;
    private final String dateTime;
    private final double amount;
    private final boolean isSuccess;
    
    public Transfer(String[] data) throws Exception {
        if (data.length == 5) {
            this.fromId = data[1];
            this.toId = data[2];
            this.dateTime = data[3];
            
            try {
                this.amount = Double.parseDouble(data[4]);
            } catch (NumberFormatException e) {
                throw new Exception("Unable to parse for amount");
            }
            
            this.isSuccess = Boolean.parseBoolean(data[5]);
        } else {
            throw new ArrayIndexOutOfBoundsException("Transfer database requires 5 parameters!");
        }
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }
}
