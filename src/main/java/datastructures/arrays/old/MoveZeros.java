package datastructures.arrays.old;

public class MoveZeros {
    public static void main(String[] args) {
        MoveZeros moveZeros = new MoveZeros();
        int num[][] = {{0,1,0,3,12},{0},{1}};
        for( int n[]: num) {
            moveZeros.moveZeroes(n);
        }
    }

    public void moveZeroes(int [] nums) {
        int k =0 ;
        for ( int i = 0;i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        while (k<nums.length) {
            nums[k]=0;
            k++;
        }
    }
}
