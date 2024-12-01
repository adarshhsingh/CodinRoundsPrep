package introToAlgorithm.sorting;

import reusables.PrintModules;

public class CountingSort {

    public static void main(String[] args) {
        int arr[] = {10,6,7,5};
        countingSort(arr);

    }


    public static void countingSort(int[] input) {

        // identifying the range
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i : input) {
            if(min > i) min = i;
            if(max < i) max = i;
        }

        // size of count array
        int size = max - min + 1; // 10,6,7,5  min=5, max=10, 10-5+1= 6 (0,1,2,3,4,5)
        // setup count array
        int count[] = new int[size]; // 0,0,0,0,0,0

        PrintModules.print("initialized count array");
        PrintModules.print(count);

        // fill the count array
        for(int i: input) {
            count[i-min]++;
        }
        PrintModules.print("filled the count array");
        PrintModules.print(count); // 1,1,1,0,0,1

        // accumulate the count
        for(int i=1; i < size; i++) {
            count[i] += count[i-1];
        }
        PrintModules.print("accumulate the count");
        PrintModules.print(count); // 1,2,3,3,3,4

        int[] sortedArray = new int[input.length];
        // to maintain stability , order of same numbers
        for(int i = input.length-1; i >= 0; i--) {
            int num = input[i];
            int sortedIndex = count[num-min] - 1;
            sortedArray[sortedIndex] = num;
            count[num-min]--;
        }

        PrintModules.print(sortedArray);
    }




}
