package leetcode.twoPointers;

import reusables.PrintModules;

import java.util.*;

public class MaxNumberOfKsumPairs {

    public static void main(String[] args) {
        int arr[]={3,1,3,4,3};
        System.out.println(maxOperations(arr,5));

        Set<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        List<List<Integer>> answer = new ArrayList<>();

    }

    public static int maxOperations(int[] nums, int k) {

        Map<Integer, Integer> numMap = new HashMap<>();
        for(int i:nums) {
            if(numMap.containsKey(i)) {
                numMap.put(i,numMap.get(i)+1);
            } else {
                numMap.put(i,1);
            }
        }
        // we need to

        int count = 0;

        for(Map.Entry<Integer, Integer> entry : numMap.entrySet()) {
            int value = entry.getKey();
            int occurrence = entry.getValue();

            int complement = k-value;

            if(numMap.containsKey(complement)) {
                if(value==complement) {
                    count += (occurrence/2);

                } if(value<complement)
                    count += Math.min(occurrence,numMap.get(complement));
                }
            }

        return count;

    }



        /**
         0 1 2 3 4
         [3,1,3,4,3]


         loop till list size is greater than equal to 2
         firstIndex=0, find second index, such that nums[secondIndex]=(k-nums[firstIndex])
         if found - remove firstIndex and secondIndex  and count++;
         if not found - remove firstIndex




        List<Integer> numList = new ArrayList<>();
        for(int i:nums) {
            numList.add(i);
        }

        int count = 0;

        while(numList.size()>=2) {
            int firstIndex = 0;
            int firstNum = numList.get(0);
            int secondIndex = 1;
            int findNum = k-firstNum;
            boolean found = false;
            while(secondIndex < numList.size()) {
                if(numList.get(secondIndex)==findNum) {
                    found = true;
                    break;
                }
                ++secondIndex;
            }
            if(found == true) {
                numList.remove(secondIndex);
                numList.remove(firstIndex);
                count++;
            }else {
                numList.remove(firstIndex);
            }
        }

        return count;
         */
}
