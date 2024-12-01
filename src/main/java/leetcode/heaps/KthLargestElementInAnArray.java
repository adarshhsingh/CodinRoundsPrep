package leetcode.heaps;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {

        // to find Kth largest element, we will have to maintain a heap of size K
        // we will maintain a MIN_HEAP which keeps minimum at the top
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // first we will enter the element in PQ - till size if K
        // after size is K, we will compare the new item with the top element of min heap.
        // all elements below would be greater than top element
        // if our new element is greater than the top of min-heap, we remove the top of min-heap
        // and insert the new element

        for(int n : nums) {
            if(queue.size() >= k) {
                if(queue.peek() < n) {
                    queue.remove();
                    queue.add(n);
                }
            } else {
                queue.add(n);
            }
        }

        return queue.peek();
    }

    public static void main(String[] args) {
        int[][] nums = {{3,2,1,5,6,4},{3,2,3,1,2,4,5,5,6}};
        int[] k = {2,4};

        KthLargestElementInAnArray obj = new KthLargestElementInAnArray();
        for(int i = 0; i < k.length; i++) {
            System.out.println(obj.findKthLargest(nums[i],k[i]));
        }
    }
}
