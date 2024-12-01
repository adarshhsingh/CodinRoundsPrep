package datastructures.arrays.old;

import math.MathModule;
import reusables.PrintModules;

public class ArrayRotation {


    public static void main(String[] args) {
        int input[]={1,2,3,4,5,6,7};
        int output[];

        System.out.println("00:01".compareTo("00:02")); // -1
        System.out.println("01:01".compareTo("00:02")); // 1
        System.out.println("11:01".compareTo("01:02")); // 1
        System.out.println("22:22".compareTo("23:59")); // -1

       // System.out.println("*** Default Array ***");
      //  PrintModules.print(input);


        // Algo - rotation by reversing the array 3 times
        System.out.println("*** Reverse Array ***");
      //  output = rotataArrayByN(input, 2);
       // PrintModules.print(output);


        // algo- rotation by gcd
     //   PrintModules.print(MathModule.GCD(98,56));

    }

    public static int[] rotataArrayByN(int arr[] , int n)
    {
        // first rotate from first `n` numbers
        arr=reverseArray(arr, 0, n-1);

        // then rotate the remaining number which were untouched
        arr=reverseArray(arr,n,arr.length-1);

        // now reverse the whole array
        arr=reverseArray(arr,0,arr.length-1);

        return arr;
    }

    private static int[] reverseArray(int[] arr, int firstIndex, int lastIndex) {

        int temp;

        while(firstIndex < lastIndex) {
            temp = arr[firstIndex];
            arr[firstIndex] = arr[lastIndex];
            arr[lastIndex] = temp;
            firstIndex ++;
            lastIndex --;
        }
        return arr;
    }
}
