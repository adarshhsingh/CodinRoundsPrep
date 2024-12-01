package introToAlgorithm.sorting;

import reusables.PrintModules;

public class InsertionSort {

    public static void main(String[] args) {
        int [] arr1 = new int[]{33, 30, 22, 14};
        Integer[] arr = new Integer[arr1.length];
        for(int i = 0; i < arr1.length; i++) {
            arr[i] = arr1[i];
        }
        PrintModules.print(arr);
        long currentTime = System.nanoTime();
        insertionSort(arr);
        long timeAfterExecution = System.nanoTime();
        System.out.println("Time taken in nano time :"+ (timeAfterExecution-currentTime));
        PrintModules.print(arr);
    }

    static Comparable[] insertionSort(Comparable arr[]) {
        // we skipped arr[0]
        // We start by showing that the loop invariant holds before the first
        // loop iteration
        for(int i=1;i<arr.length;i++) {
            Comparable key = arr[i];
            int j = i-1;
            System.out.println(key);
            while (j >= 0 && less(key , arr[j])) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1]=key;
            //PrintModules.print(arr);
        }
        return arr;
    }

    static boolean less(Comparable a, Comparable b) {
        return (a.compareTo(b) < 0);
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
