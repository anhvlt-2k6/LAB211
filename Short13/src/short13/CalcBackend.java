package short13;

import java.util.ArrayList;

/**
 * Short 13 - Calculator Backend
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-14
 */
public final class CalcBackend {
    
    private double mortageValue, percentage, monthlyPayment;

    /**
     * Get values (payment per month)
     * @return 2D dynamic-array, contain of month-owed pairs
     * @throws Exception in case customer cannot pay for house fully
     */
    public ArrayList<double[]> getValues() throws Exception {
        ArrayList<double[]> values = new ArrayList<>();
        
        // Assume that the mortage value after payment is higher or equal the
        //  monthly payment. Loop forver
        double assum = this.mortageValue * (1 + (this.percentage / 12)) - this.monthlyPayment; 
        if (assum >= this.mortageValue) {
            throw new Exception("Customer will never pay for the house fully.");
        }
        
        // While the owned value remains, continue for calculating
        while (mortageValue > 0.0) {
            
            // The pair value (month - remaining)
            double[] value = new double[2];
            
            // Split case
            if (mortageValue < monthlyPayment) {
                // in case of the remaining is smaller than monthly, set monthly 
                //  to remaning and remaining to 0
                this.monthlyPayment = this.mortageValue;
                this.mortageValue = 0;
                
                // set pairs
                value[0] = this.monthlyPayment;
                value[1] = this.mortageValue;
                
                // add pairs into list
                values.add(value);
            } else {
                // the other case - normal problem case
                
                // set the first param of pair to monthly payment
                value[0] = this.monthlyPayment;
                
                // set the remaining value as the what the problem mention
                mortageValue = this.mortageValue * (1 + (this.percentage / 12)) - this.monthlyPayment;
                value[1] = mortageValue;
                
                // add pairs into list
                values.add(value);
            }
        }
        
        // return list of pairs
        return (values);
    }
    
    /**
     * Set mortage value (or remaining value)
     * @param mortageValue double as mortage value
     */
    public void setMortageValue(double mortageValue) {
        this.mortageValue = mortageValue;
    }

    /**
     * Set percentage value
     * @param percentage double as percentage (set to 0.01 to operate easier)
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage * 0.01;
    }

    /**
     * Set monthly payment
     * @param monthlyPayment double as monthly payment
     */
    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
