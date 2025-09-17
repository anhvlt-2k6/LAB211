package short06;

import java.util.HashSet;
import java.util.Scanner;

public class Short06 {
    
    Scanner sc;
    int[] duplicatedArr;
    HashSet<Integer> nonDuplicatedArr;
    
    public Short06() {
        sc = new Scanner(System.in);
        nonDuplicatedArr = new HashSet<>();
    }
    
    public void displayUserInterface() {
        System.out.print("Please enter size of array: ");
        
        try {
            int length = Integer.parseInt(sc.nextLine());
            
            if (length > 0) {
                duplicatedArr = new int[length];
                
                for (int i = 0; i < length; i++) {
                    System.out.print(String.format("Element[%d] = ", i));
                    duplicatedArr[i] = Integer.parseInt(sc.nextLine());
                }
            }
            
            System.out.println("The original array:");
            for (int i : duplicatedArr) {
                System.out.print(i + "\t");
                nonDuplicatedArr.add(i);
            }
            
            System.out.println("\nThe array after removing duplicate elements:");
            for (int i : nonDuplicatedArr) {
                System.out.print(i + "\t");
            }
        } catch (NumberFormatException number_ex) {
            // 
        }
    }
    
    public static void main(String[] args) {
        Short06 o6 = new Short06();
        
        o6.displayUserInterface();
    }
}