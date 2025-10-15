package short14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class NumberProcessorBackend {
    
    /**
     * Check if Prime number
     * @param input 
     * @return boolean if the int is 
     */
    private boolean isPrime(int input) {
        boolean isPrime = true;
        
        for (int i = 2; i < (input / 2) + 1; i++) {
            if (input % i == 0 && input != i) {
                isPrime = false;
                break;
            }
        }
        
        return (isPrime);
    }
    
    private int getFibbonaci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        
        return this.getFibbonaci(n - 1) + this.getFibbonaci(n - 2);
    }
    
    public boolean isFib(int n) {
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
    
    public int sumofDigits(String s) {
        int sum = 0;
        
        char[] a = s.toCharArray();
        
        for (char c : a) {
            sum += c - '0';
        }
        
        return (sum);
    }
}
