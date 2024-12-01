package datastructures.arrays.julypreperation.easy;

public class NumberOfGoodPairs {

    public static void main(String[] args) {
        int a [] = {6,5,1,5,7,7,9,1,5,7,1,6,10,9,7,4,1,8,7,1,1,8,6,4,7,4,10,5,3,9,10,1,9,5,5,4,1,7,4,2,9,2,6,6,4,2,10,3,5,3,6,4,7,4,6,4,4,6,3,4,10,1,10,6,10,4,9,6,6,4,8,6,9,5,4};
        NumberOfGoodPairs obj = new NumberOfGoodPairs();
        int i = 0;
        int j = 2147483647;
        String s = "+1";
        System.out.println(Integer.parseInt(s));

        System.out.println(i-j);
        //System.out.println(i+1);
        //System.out.println(obj.numIdenticalPairs(a));
    }
        public int numIdenticalPairs(int[] nums) {
            int count[] = new int[100];
            for (int i = 0; i<nums.length; i++) {
                count[nums[i]-1]++;
            }
            int pairs = 0;

            for (int i = 0; i<count.length; i++) {
                if(count[i]>1) {
                    pairs += binomialCoefficient(count[i]);
                }
            }
            return pairs;
        }

        int binomialCoefficient(int n) {
            return (n*(n-1))/2;
        }

        int fact(int n) {
            System.out.println("*** nC2 of n = "+n);
            if(n==0) return 1;
            int ans = 1;
            while(n>0) {
                ans = ans*n;
                System.out.println(ans);
                --n;

            }
            return ans;
        }
}
