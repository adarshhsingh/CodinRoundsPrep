package leetcode.hashtable;

import reusables.PrintModules;

import java.util.HashMap;
import java.util.Map;

public class DotProductOfSparseVectors {
    public static void main(String[] args) {
        int nums1[] = {1,0,0,2,3};
        int nums2[] = {0,3,0,4,0};
        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);
        int ans = v1.dotProduct(v2);
        PrintModules.print(ans);
    }
}

class SparseVector {
    // 1. occurence of 0 is very high
    // 2. nums range from 0 to 100 only

    public Map<Integer, Integer> indexNumber;

    SparseVector(int[] nums) {
        indexNumber = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                indexNumber.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        // we need to find the nums which has
        int dotProduct = 0;
        for(Map.Entry entry : vec.indexNumber.entrySet()) {
            int index = (int)entry.getKey();
            int num   = (int)entry.getValue();

            if(indexNumber.containsKey(index)) {
                dotProduct += num * indexNumber.get(index);
            }
        }
        return dotProduct;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
