package short09;

import java.util.Arrays;

/**
 * Short 09 - Array object
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public final class Array {
    
    // arr as tree set of integer
    private final int[] arr;
    private int arrSize;
    
    /**
     * Constructor of the array
     * @param n for indexing
     */
    public Array(int n) {
        arrSize = n + 1;
        // Initialize the arr. n + 1 for adding new value
        arr = new int[arrSize];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
    }
    
    /**
     * Add value but still keep the array in order
     * @param value as integer in value
     * @param index as index
     */
    public void addValue(int index, int value) {
        if (index >= arr.length) {
            return;
        }
        
        arr[index] = value;
        if (index - 1 >= this.arrSize) {
            this.arrSize = index + 1;
        }
    }
    
    /**
     * Sorting array
     */
    public void sort() {
        // Loop with pointer 1
        for (int c = 0; c < (this.arrSize - 1); c++) {
            
            // Loop with pointer 2 inner pointer 1
            for (int i = 0; i < (this.arrSize - 1); i++) {
                
                // If comparator is larger than 0, swap
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }
    
    /**
     * Add new value in-order
     * @param n as that new value
     */
    public void addNewInorder(int n) {
        // Insert to the last
        arr[arr.length - 1] = n;
        
        // Move element in-order correctly
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i - 1] < arr[i]) {
                break;
            }
            int tmp = arr[i - 1];
            arr[i - 1] = arr[i];
            arr[i] = tmp;
        }
    }
    
    /**
     * Get the array
     * @return int in array
     */
    public int[] getArr() {
        return Arrays.copyOf(arr, arrSize);
    }
}
