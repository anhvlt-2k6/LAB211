package short08;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Short 08 - Array
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-10-01
 */
public final class Array {
    
    /**
     * arr as array
     * ArrLength as the actual length of array
     */
    private final int[] arr;
    private int arrLength;
    
    /**
     * Constructor of array
     */
    public Array() {
        // create new array
        arr = new int[100];
        
        // initialize default value
        for (int i = 0; i < 100; i++) {
            arr[i] = Integer.MIN_VALUE;
        }
        
        // set default length as 0 (empty)
        arrLength = 0;
    }
    
    /**
     * Add value into array
     * @param value as int
     * @return whether adding new value is success or not
     */
    public boolean addValue(int value) {
        
        if (arrLength >= arr.length) {
            return false;
        }
        
        arr[arrLength++] = value; // assign vaule
        return true;
    }
    
    /**
     * Get the current array length pointer of next active location
     * @return int as Length of array
     */
    public int getArrLength() {
        return arrLength;
    }
    
    /**
     * Find index of value
     * @param value as int value
     * @return index of its on string
     */
    public ArrayList<Integer> indexofValue(int value) {
        ArrayList<Integer> foundIndex = new ArrayList<>();
        
        // loop for value
        for (int i = 0; i < arrLength; i++) {
            // if found, assign set the index and exit the loop
            if (arr[i] == value) {
                foundIndex.add(i);
            }
        }
        
        // return index
        return (foundIndex);
    }
    
    /**
     * Get value in bound
     * @param lower as int
     * @param upper as int
     * @return ArrayList of values inbound
     */
    public ArrayList<Integer> getInboud(int lower, int upper) {
        // Initialize the inbound
        ArrayList<Integer> arrinbound = new ArrayList<>();
        
        // loop for array
        for (int i = 0; i < this.arrLength; i++) {
            int v = arr[i];
            if (v >= lower && v <= upper) {
                arrinbound.add(v);
            }
        }
        
        // return the array list
        return (arrinbound);
    }

    /**
     * Get array
     * @return array in int, but if empty, it should be null for more practical
     */
    public int[] getArr() {
        return (arrLength == 0) ? new int[0] : Arrays.copyOf(arr, arrLength);
    }
    
    /**
     * Sorting array
     */
    public void sort() {
        // Loop with pointer 1
        for (int c = 0; c < (arrLength - 1); c++) {
            
            // Loop with pointer 2 inner pointer 1
            for (int i = 0; i < (arrLength - c - 1); i++) {
                
                // If comparator is larger than 0, swap
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }
}
