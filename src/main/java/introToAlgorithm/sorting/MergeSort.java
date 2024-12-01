package introToAlgorithm.sorting;

import reusables.PrintModules;

public class MergeSort {

    public static void main(String[] args) {
        int arr[]= new int[]{1,3,12,0,0};
        PrintModules.print(arr);
        mergeSort(arr, 0, arr.length-1);
        PrintModules.print(arr);
    }

    public static int[] mergeSort(int arr[], int left, int right) {
        System.out.printf("mergesort called for left = %d, right = %d \n", left, right);
        if(left < right) {
            int mid = (left+right)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);
            System.out.printf("merge being done for left = %d, right = %d \n", left, right);
            merge(arr, left, mid, right);
        }
        return arr;
    }

    public static int[] merge(int arr[], int left, int mid, int right) {
        // merge two array
        int lengthFirstSubArray = mid-left+1;
        int lengthSecondSubArray = right - mid;

        int firstSubArray[] = new int[lengthFirstSubArray];
        int secondSubArray[] = new int[lengthSecondSubArray];

        for(int i=left; i <lengthFirstSubArray; i++ ) {
            firstSubArray[i] = arr[left+i];
        }
        System.out.println("firstSubArray = ");
        PrintModules.print(firstSubArray);

        for(int j=0; j<lengthSecondSubArray; j++ ) {
            secondSubArray[j] = arr[mid+1+j];
        }
        System.out.println("secondSubArray = ");
        PrintModules.print(secondSubArray);

        int i=0,j=0;
        int k = left; // index of main arr

        while(i<lengthFirstSubArray && j<lengthSecondSubArray) {
            if(firstSubArray[i] <= secondSubArray[j]) {
                arr[k] = firstSubArray[i];
                ++i;
            } else {
                arr[k] = secondSubArray[j];
                ++j;
            }
            ++k;
        }

        while(i<lengthFirstSubArray) {
            arr[k] = firstSubArray[i];
            ++i;
            ++k;
        }

        while(j<lengthSecondSubArray) {
            arr[k] = secondSubArray[j];
            ++j;
            ++k;
        }

        System.out.println("arr = ");
        PrintModules.print(arr);

        return arr;
    }
}
