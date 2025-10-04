package short06;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Short 06 - Array as Integer
 * @author CE200360 - Vo Luu Tuong Anh
 * @since 2025-09-18
 */
public final class IntArr {
    private final ArrayList<Integer> duplicatedArr;
    private final Set<Integer> nonDuplicatedArr;
    
    /**
     * Constructor of IntArr
     * Initialize both arrays (duplicated and non-duplicated)
     */
    public IntArr() {
        duplicatedArr = new ArrayList<>();
        nonDuplicatedArr = new LinkedHashSet<>();
    }
    
    /**
     * Add a new Number
     * @param number in integer
     */
    public void addNumber(int number) {
        duplicatedArr.add(number);
        nonDuplicatedArr.add(number);
    }

    /**
     * Get the duplicated array
     * @return an array as duplicated (raw from user)
     */
    public ArrayList<Integer> getDuplicatedArr() {
        return duplicatedArr;
    }
    
    /**
     * Get the non-duplicated array
     * @return an array as non-duplicated (processed)
     */
    public Set<Integer> getNonDuplicatedArr() {
        return nonDuplicatedArr;
    }
}
