package datastructures.arrays.old;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that i,j,k are distinct
 * and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 */
public class TripletsWIthZeroSum {

    public static List<List<Integer>> twoSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        while( left < right) {
            int sum = nums[left] + nums[right];
            if (sum == 0) {
                List<Integer> list = Arrays.stream(new Integer[]{nums[left], nums[right]}).toList();
                result.add(list);
                // Skip duplicates
                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;
                left++;
                right--;

            } else if (sum > 0) {
                right--;

            } else {
                left++;

            }
        }

        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        for (int i = 0; i < nums.length - 2;) {
            int target = -nums[i];
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> list = Arrays.stream(new Integer[]{nums[i], nums[left], nums[right]}).toList();
                    result.add(list);
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;

                } else if (sum > target) {
                    right--;

                } else {
                    left++;

                }
            }
            while (nums[i] == nums[i + 1] && i < nums.length - 2) i++;
            ++i;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        int[] nums1 = {-3, -1, 1, 2, -2, 3};
        int[] nums2 = {-4, -2, -2, 0, 2, 2, 4};
        int[] nums3 = {-7, -5, -3, -2, -1};
        int[] nums4 = {1, 2, 3, 5, 6, 7};
        int[] nums5 = {0, 0, 0, 0};

        printArray(twoSum(nums));
        printArray(twoSum(nums1));
        printArray(twoSum(nums2));
        printArray(twoSum(nums3));
        printArray(twoSum(nums4));
        printArray(twoSum(nums5));


        printArray(threeSum(nums));
        printArray(threeSum(nums1));
        printArray(threeSum(nums2));
        printArray(threeSum(nums3));
        printArray(threeSum(nums4));
        printArray(threeSum(nums5));

    }

    public static void printArray(List<List<Integer>> list) {
        System.out.print("\n[");
        for (int i=0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if ( i != list.size() -1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }


}
