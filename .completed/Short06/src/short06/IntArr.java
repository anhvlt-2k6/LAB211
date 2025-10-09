package short06;

/**
 * Short 06 - Array as Integer
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public final class IntArr {
    
    private int[] dupArr;
    private int lengthdupArr;
    
    /**
     * Constructor of IntArr
     * Initialize both arrays (duplicated and non-duplicated)
     * @param length as length of array
     */
    public IntArr(int length) {
        dupArr = new int[length];
        
        for (int i = 0; i < length; i++) {
            dupArr[i] = Integer.MIN_VALUE;
        }
        
        lengthdupArr = 0;
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
     * Remove duplicates
     */
    public void removeDuplicate() {
        // init local arr
        int[] nondupArr = new int[lengthdupArr];
        
        for (int i = 0; i < lengthdupArr; i++) {
            nondupArr[i] = Integer.MIN_VALUE;
        }
        
        // set array as 0
        int lengthnondupArr = 0;
        
        // for to check duplication
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
        
        // set back
        dupArr = nondupArr;
    }
}
