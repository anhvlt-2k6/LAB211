package short13;

import java.util.ArrayList;

/**
 * Short 13 - Calculator Backend
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-14
 */
public final class CalcBackend {
    
    private double mortageValue, percentage, monthlyPayment;

    public ArrayList<double[]> getValues() {
        ArrayList<double[]> values = new ArrayList<>();
        
        while (mortageValue > 0.0) {
            double[] value = new double[2];
            
            if (this.mortageValue < this.monthlyPayment) {
                monthlyPayment = mortageValue;
                mortageValue = 0;
            
                value[0] = this.monthlyPayment;
                value[1] = this.mortageValue;
                
                values.add(value);
            } else {
                mortageValue = this.mortageValue * (1 + this.percentage) - this.monthlyPayment;
                
                value[0] = this.monthlyPayment;
                value[1] = this.mortageValue;
                
                values.add(value);
            }
        }
        
        return (values);
    }
    
    public void setMortageValue(double mortageValue) {
        this.mortageValue = mortageValue;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage * 0.01;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
