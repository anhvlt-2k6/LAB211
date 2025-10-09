package short06;

/**
 * Short 06 - Array as Integer
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public final class IntArr {
    
    private final int[] dupArr;
    private final int[] nondupArr;
    private int lengthdupArr, lengthnondupArr;
    
    /**
     * Constructor of IntArr
     * Initialize both arrays (duplicated and non-duplicated)
     * @param length as length of array
     */
    public IntArr(int length) {
        dupArr = new int[length];
        nondupArr = new int[length];
        
        for (int i = 0; i < length; i++) {
            dupArr[i] = Integer.MIN_VALUE;
            nondupArr[i] = Integer.MIN_VALUE;
        }
        
        lengthdupArr = 0;
        lengthnondupArr = 0;
    }
    
    /**
     * Add a new Number
     * @param number in integer
     */
    public void addNumber(int number) {
        dupArr[lengthdupArr] = number;
        lengthdupArr += 1;
    }

    /**
     * Get the duplicated array
     * @return an array as duplicated (raw from user)
     */
    public int[] getDuplicatedArr() {
        return dupArr;
    }
    
    /**
     * Get the non-duplicated array
     * @return an array as non-duplicated (processed)
     */
    public int[] getNonDuplicatedArr() {
        return nondupArr;
    }
    
    /**
     * Remove duplicates
     */
    public void removeDuplicate() {
        for (int i : dupArr) {
            boolean isExisted = false;
            
            for (int j : nondupArr) {
                if (i == j) {
                    isExisted = true;
                    break;
                }
            }
            
            if (!isExisted) {
                nondupArr[lengthnondupArr] = i;
                lengthnondupArr += 1;
            }
        }
    }
}
