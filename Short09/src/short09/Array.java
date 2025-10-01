package short09;

import java.util.TreeSet;

public final class Array {
    
    private final TreeSet<Integer> arr;
    
    public Array() {
        arr = new TreeSet<>();
    }
    
    public void addElement(int value) {
        arr.add(value);
    }

    public TreeSet<Integer> getArr() {
        return arr;
    }
}
