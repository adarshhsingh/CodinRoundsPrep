package datastructures.arrays.meta;
import java.util.Arrays;

/**
 * <a href="https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3012/">
 *     NextPermutation
 * </a>
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1]
 * does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * --------
 * Example 1:
 * Input: nums = {1,2,3}
 * Output: {1,3,2}
 * --------
 * Example 2:
 * Input: nums = {3,2,1}
 * Output: {1,2,3}
 * -------
 * Example 3:
 * Input: nums = {1,1,5}
 * Output: {1,5,1}
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        // formulate next Perm here
        // how to compare - can't with int - since nums.length <=100
        int end, start = nums.length;
        // best replace would be the nearest
        int minReplace = Integer.MIN_VALUE;
        int []replaceBetween = new int[2];
        boolean nextPermPossisble = false;
        for (end = nums.length-1; end>=0; end--) {
            start = end;
            // move left till we don't find a smaller number than num[end]
            while (start >=0 && nums[start] >= nums[end]) {
                start--;
            }
            if(start>=0) {
                nextPermPossisble = true;
                if(start > minReplace) {
                    replaceBetween[0] = start;
                    replaceBetween[1] = end;
                    minReplace = start;
                }
            }
        }
        if(nextPermPossisble) {
            swap(replaceBetween[0], replaceBetween[1], nums);
            reverse(replaceBetween[0]+1, nums);
        } else {
            reverse(0,nums);
        }
        print(nums);
    }

    public void swap(int a, int b, int []nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void reverse(int start, int []nums) {
        int end = nums.length-1;
        while(start<end) {
            swap(start,end,nums);
            start++;
            end--;
        }
    }

    public void print(int []a) {
        System.out.print('[');
        for(int i=0;i<a.length;i++) {
            System.out.print(a[i]);
            if(i!=a.length-1) System.out.print(",");
        }
        System.out.println(']');
    }

    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();
        int []nums = {1}; // 1
        obj.nextPermutation(nums);
        nums = new int[]{0}; // 0
        obj.nextPermutation(nums);
        nums = new int[]{0,1}; // 1,0
        obj.nextPermutation(nums);
        nums = new int[]{1,0}; // 0,1
        obj.nextPermutation(nums);
        nums = new int[]{1,1}; // 1,1
        obj.nextPermutation(nums);
        nums = new int[]{1,2}; // 2,1
        obj.nextPermutation(nums);
        nums = new int[]{1,2,3};  // 1,3,2
        obj.nextPermutation(nums);
        nums = new int[]{1,3,2};  // 2,1,3
        obj.nextPermutation(nums);
        nums = new int[]{2,1,3};  // 2,3,1
        obj.nextPermutation(nums);
        nums = new int[]{2,3,1};  // 3,1,2
        obj.nextPermutation(nums);
        nums = new int[]{3,1,2};  // 3,2,1
        obj.nextPermutation(nums);
        nums = new int[]{3,2,1}; // 1,2,3
        obj.nextPermutation(nums);
        nums = new int[]{4,2,0,2,3,2,0}; // 4,2,0,3,0,2,2
        obj.nextPermutation(nums);
    }
}
