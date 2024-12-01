package educative;

public class ModifiedBinarySearchPattern {

    public static void main(String[] args) {
        int arr[]= {-10, 11, 60, 70};
        System.out.println(binarySearch(arr, 60));
        arr= new int[]{1, 6, 8, 10};
        System.out.println(binarySearch(arr, 1));
    }
    /**
     * We are given an array of integers, nums, sorted in ascending order,
     * and an integer value -> target.
     *
     * If the target exists in the array, return its index. If the target does not exist, return -1.
     *
     * Constraints:
     * 1 ≤  nums.length ≤ 10^3
     * -10^4 <= nums[i] , target <= 10^4
     *
     * All integers in nums are unique.
     * nums is sorted in ascending order.
     */

    public static int binarySearch (int []nums, int target) {

        int start = 0;
        int end = nums.length-1;
        int mid = (start+end)/2;
        while(start<=end) {
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] > target) {
                end = mid-1;
                mid = (start+end)/2;
                continue;
            }
            start = mid + 1;
            mid = (start+end)/2;
        }
        return -1;
    }
}
