package testapp;

public class TestApp {
    
    public static void main(String[] args) {
        Integer[] a = new Integer[] {1,2,3};
        
        for (int i : a) {
            i = 0;
        }
        
        //int[] a = IntStream.concat(a1, a2);
        System.out.println(a[0] + " " + a[1] + " " + a[2]);
    }
}
