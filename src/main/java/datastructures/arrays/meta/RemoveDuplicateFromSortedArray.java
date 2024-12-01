package datastructures.arrays.meta;

/**
 * https://leetcode.com/explore/interview/card/facebook/5/array-and-strings/3011/
 */
public class RemoveDuplicateFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int right = 0;
        int nextNum ;
        int counter = 0;
        for (int left = 0; left<nums.length && right<nums.length; left++) {
            nextNum = nums[right];
            nums[left] = nextNum;
            counter++;
            while (right<nums.length && nums[right]==nextNum) right++;
        }

        return counter;
    }

    public void testRemoveDuplicates(int nums[], int expectedNums[], int k) {
        if(k != expectedNums.length) System.out.println("Fail");
        for (int i = 0; i < k; i++) {
            if( nums[i] != expectedNums[i]) {
                System.out.println("Fail");
            }
        }
    }

    public static void main(String[] args) {
        RemoveDuplicateFromSortedArray obj = new RemoveDuplicateFromSortedArray();
        int []nums = {1,1,2};
        int []expectedNums = {1,2};
        int k = obj.removeDuplicates(nums); // Calls your implementation
        obj.testRemoveDuplicates(nums,expectedNums,k);
        nums = new int[]{1,1,1};
        expectedNums = new int[]{1};
        k = obj.removeDuplicates(nums); // Calls your implementation
        obj.testRemoveDuplicates(nums,expectedNums,k);
        nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        expectedNums = new int[]{0,1,2,3,4};
        k = obj.removeDuplicates(nums); // Calls your implementation
        obj.testRemoveDuplicates(nums,expectedNums,k);
    }
}
