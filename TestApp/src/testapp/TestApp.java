package testapp;

import java.util.PriorityQueue;

public class TestApp {
    
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        minHeap.add(3);

        System.out.println("Min-Heap elements (smallest first):");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll());
        }
    }
}
