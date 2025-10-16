package short14;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Short 14 - Number Processor Backend
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-10-15
 */
public final class NumberProcessorBackend {
    
    /**
     * Check if Prime number
     * @param input 
     * @return boolean if the int is 
     */
    private boolean isPrime(int input) {
        // Return parameter
        boolean isPrime = true;
        
        // Loop from 2 to (input / 2 + 1) for any number can divide
        for (int i = 2; i < (input / 2) + 1; i++) {
            // If that number can divide is not itself, it not prime
            //  (break the loop)
            if (input % i == 0 && input != i) {
                isPrime = false;
                break;
            }
        }
        
        // return value
        return (isPrime);
    }
    
    /**
     * Get the fibbonaci number
     * @param n as input n
     * @return in recursive of itself for f(n - 1) + f(n - 2)
     */
    private int getFibbonaci(int n) {
        // Final case
        // For n = 1 or n = 2, it is 1
        // Else case the 0
        if (n == 1 || n == 2) {
            return 1;
        } else if (n <= 0) {
            return 0;
        }
        
        // Return value with recursive itself
        return this.getFibbonaci(n - 1) + this.getFibbonaci(n - 2);
    }
    
    /**
     * Check if a number is in Fibonacci number
     * @param n
     * @return
     */
    public boolean isFib(int n) {
        // 
        boolean isFib = false;
        
        HashSet<Integer> fibUnderRange = new HashSet<>();
        
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int fib = this.getFibbonaci(i);
            if (fib <= n) {
                fibUnderRange.add(fib);
            } else {
                break;
            }
        }
        
        isFib = fibUnderRange.contains(n);
        
        return (isFib);
    } 
    
    /**
     *
     * @param limit
     * @return
     */
    public ArrayList<Integer> getPrimes(int limit) {
        int curr = 2;
        ArrayList<Integer> list = new ArrayList<>();
        
        while (list.size() < limit) {
            if (this.isPrime(curr)) {
                list.add(curr);
            }
            
            curr += 1;
        }
        
        return list;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public int sumofDigits(String s) {
        int sum = 0;
        
        char[] a = s.toCharArray();
        
        for (char c : a) {
            sum += c - '0';
        }
        
        return (sum);
    }
}
