package datastructures.arrays.old;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3019/
 *
 * Given an array of integers nums and an integer k,
 * return the total number of sub-arrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubArraySumEqualsK {

    public static void main(String[] args) {
       int arr[] = {1,1,1};
       int k = 2;

       System.out.println(subarraySum(arr,k));

       arr = new int[]{1,2,3};
       k = 3;

       System.out.println(subarraySum(arr,k));


        arr = new int[]{3,1,1,4,2,2,3,1,1,1,1,2};
        k = 4;

        System.out.println(subarraySum(arr,k));

        arr = new int[]{1,-1,0};
        k = 0;

        System.out.println(subarraySum(arr,k));

        arr = new int[]{1,-1,0};
        k = -1;

        System.out.println(subarraySum(arr,k));

        arr = new int[]{0,0};
        k = 0;

        System.out.println(subarraySum(arr,k));
    }



    public static int subarraySum(int[] nums, int k) {
        int numberOfSubArray = 0;
        Map<Integer,Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0,1); // for case when first index number = k
        int currentSum = 0;
        for( int num : nums) {
            currentSum += num;
            if(prefixSumCount.containsKey(currentSum-k)) {
                numberOfSubArray += prefixSumCount.get(currentSum-k);
            }
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0)+1);
        }
        return numberOfSubArray;
    }

    public static int subarraySumByTwoPointerMethod(int[] nums, int k) {
        // two pointer
        int second = 0;
        int numberOfSubArray = 0;
        for(int first = 0; first < nums.length; first++) {
            second = first;
            int sum = nums[first];
            if(sum == k) {
                numberOfSubArray++;
            }
            second++;
            while (second < nums.length) {
                sum += nums[second];
                if( sum == k) {
                    numberOfSubArray++;
                }
                second++;
            }
        }
        return numberOfSubArray;
    }
}
