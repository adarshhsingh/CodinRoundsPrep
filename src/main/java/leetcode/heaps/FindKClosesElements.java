package leetcode.heaps;

import reusables.PrintModules;

import java.util.*;

public class FindKClosesElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();

        int left = 0;
        int right = arr.length - k;
        int mid = 0;
        while(left < right) {
            mid = left + (right - left)/2;
            if(x - arr[mid] > arr[mid+k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        for(int i = left; i < left + k ; i++) {
            result.add(arr[i]);
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] input = {
                {1, 2, 3, 4, 5},
                {1, 1, 2, 3, 4, 5},
                {1, 2, 2, 3, 4},
                {1, 3, 3, 4, 5},
                {1, 1, 1, 10, 10 , 10},
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4}
        };
        int[] k = {4, 4, 2, 2, 1, 4};
        int[] x = {3, -1, 2, 3, 9, 3};
        FindKClosesElements obj = new FindKClosesElements();
        for(int i = 0; i < k.length; i++) {
            List<Integer> output = obj.findClosestElements(input[i], k[i], x[i]);
            PrintModules.print(output);
        }
    }
}
