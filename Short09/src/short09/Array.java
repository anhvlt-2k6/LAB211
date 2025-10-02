package short09;

import java.util.ArrayList;

/**
 * Short 09 - Array object
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-10-02
 */
public final class Array {
    
    // arr as tree set of integer
    private ArrayList<Integer> arr;
    
    /**
     * Constructor of the array
     */
    public Array() {
        // Initialize the arr
        arr = new ArrayList<>();
    }
    
    /**
     * Add value but still keep the array in order
     * @param value as integer in value
     */
    public void addInorder(int value) {
        // Do the lower bound and upper bound.
        //  Note that lower bound is less than or equal, else the upper
        ArrayList<Integer> lowerBound = new ArrayList<>();
        ArrayList<Integer> upperBound = new ArrayList<>();
        
        // Loop for elements in array. 
        //  If there is any element match any criteria, add it into the array list above
        for (int i : arr) {
            if (i <= value) {
                lowerBound.add(i);
            } else {
                upperBound.add(i);
            }
        }
        
        // combine value into main array
        arr = lowerBound;
        arr.add(value);
        arr.addAll(upperBound);
    }

    /**
     * Get the array 
     * @return ArrayList as the primary array for displaying
     */
    public ArrayList<Integer> getArr() {
        return arr;
    }
}
