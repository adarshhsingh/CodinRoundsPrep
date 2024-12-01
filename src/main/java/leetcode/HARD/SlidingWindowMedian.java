package leetcode.HARD;

import reusables.PrintModules;

import java.util.*;

public class SlidingWindowMedian {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> heapMinForUpperHalf = new PriorityQueue<>();
        PriorityQueue<Integer> heapMaxForLowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        double[] medians = new double[n - k + 1];

        for(int i = 0; i < n; i++) {

            // add new number to one of the heap
            if(heapMaxForLowerHalf.isEmpty() || nums[i] <= heapMaxForLowerHalf.peek()) {
                heapMaxForLowerHalf.add(nums[i]);
            } else {
                heapMinForUpperHalf.add(nums[i]);
            }

            // balance if the heap - i,e, max diff should be 1
            if(heapMaxForLowerHalf.size() > heapMinForUpperHalf.size() + 1) {
                heapMinForUpperHalf.add(heapMaxForLowerHalf.poll());
            } else if(heapMinForUpperHalf.size() > heapMaxForLowerHalf.size()) {
                heapMaxForLowerHalf.add(heapMinForUpperHalf.poll());
            }

            // Once we reach the window size k, record the median)
            if(i >= k - 1) {
                if (heapMaxForLowerHalf.size() == heapMinForUpperHalf.size()) {
                    medians[i - k + 1] = ((double) heapMaxForLowerHalf.peek() + heapMinForUpperHalf.peek()) / 2;
                } else {
                    medians[i - k + 1] = heapMaxForLowerHalf.peek();
                }

                // Remove the element that is sliding out of the window
                int elementToRemove = nums[i - k + 1];
                if (elementToRemove <= heapMaxForLowerHalf.peek()) {
                    heapMaxForLowerHalf.remove(elementToRemove);
                } else {
                    heapMinForUpperHalf.remove(elementToRemove);
                }

                // Re-balance the heaps after removal
                if(heapMaxForLowerHalf.size() > heapMinForUpperHalf.size() + 1) {
                    heapMinForUpperHalf.add(heapMaxForLowerHalf.poll());
                } else if(heapMinForUpperHalf.size() > heapMaxForLowerHalf.size()) {
                    heapMaxForLowerHalf.add(heapMinForUpperHalf.poll());
                }

            }
        }

        return medians;
    }



    public static void main(String[] args) {
        int[][] nums = {{1,3,-1,-3,5,3,6,7}, {1,2,3,4,2,3,1,4,2}, {2147483647,2147483647}};
        int[] k = {3, 3, 2};

        SlidingWindowMedian obj = new SlidingWindowMedian();

        for(int i = 0; i < k.length; i++) {
            PrintModules.print(obj.medianSlidingWindow(nums[i], k[i]));
        }
    }
}
