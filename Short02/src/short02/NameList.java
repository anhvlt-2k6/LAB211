package short02;

/**
 * Short 02 - Name Handler
 * @author CE200360 Vo Luu Tuong Anh
 * @since 2025-09-14
 */
public class NameList {
    
    /**
     * The property of the class.
     * @variable studentList
     */
    private final String[] nameList;
    
    /**
     * Constructor of NameList. Take the arr_length as the length of the array itself
     * @param arr_length Length of the array
     */
    public NameList(int arr_length) {
        nameList = new String[arr_length];
    }
    
    /**
     * Add/Modify an element into the array
     * @param index Index of array
     * @param value Value of that index
     */
    public void addElement(int index, String value) {
        nameList[index] = value;
    }
    
    /**
     * Display contents of array. In this case, these are the names
     */
    public void displayNameArray() {
        // Loop through the nameList
        for (int i = 0; i < nameList.length; i++) {
            System.out.println(String.format("%d. %s", (i + 1), nameList[i]));
        }
    }
    
    /**
     * Sort the array alphabetical (A-Z)
     */
    public void sortNameArray() {
        // Loop with pointer 1
        for (int c = 0; c < (nameList.length - 1); c++) {
            
            // Loop with pointer 2 inner pointer 1
            for (int i = 0; i < (nameList.length - 1); i++) {
                
                // Comparator using compareTo
                int comp = nameList[i].compareTo(nameList[i + 1]);
                
                // If comparator is larger than 0, swap
                if (comp > 0) {
                    String temp = nameList[i];
                    nameList[i] = nameList[i + 1];
                    nameList[i + 1] = temp;
                }
            }
        }
    }
}
