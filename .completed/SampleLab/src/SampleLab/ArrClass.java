package SampleLab;

import java.util.Random;

/**
 * Sample Lab
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-11
 */
public class ArrClass {
    
    private int[] arr;
    Random rd;
    
    /**
     * The constructor of the class
     * @param size Must be non-negative number
     * @throws Exception when the size is not valid of initialize new array
     */
    public ArrClass(int size) throws Exception {
        // Create new Random
        rd = new Random();
        
        // If the size if non-negative, throw new exception
        if (size > 0) {
            arr = new int[size];
            // Assign each value of array to a random value
            for (int i = 0; i < size; i++) {
                arr[i] = rd.nextInt(10);
            }
        } else {
            throw new Exception("size param should be a possitive integer");
        }
        
    }
    
    /**
     * Display the contents of array
     */
    public void displayArr() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    
    /**
     * Sorting array
     */
    public void sortArray() {
        
        // Get the length of the array
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Assume that the current index is lowest
            int min_idx = i;

            // Look for the rest of array
            for (int j = i + 1; j < n; j++) {
                // If any of the rest lower than the current, set to the lowest
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            
            // Swap
            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;           
        }
    }
}
