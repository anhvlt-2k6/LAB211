package sample_lab;

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
     *
     * @param size
     * @throws Exception when the size is not valid of initialize new array
     */
    public ArrClass(int size) throws Exception {
        rd = new Random();
        
        if (size > 0) {
            arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = rd.nextInt(10);
            }
        } else {
            throw new Exception("size param should be a possitive integer");
        }
        
    }
    
    /**
     *
     */
    public void DisplayArr() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    
    /**
     *
     */
    public void SortArray() {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            
            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;           
        }
    }
}
