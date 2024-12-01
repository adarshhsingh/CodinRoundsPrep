package introToAlgorithm.sorting;

import reusables.PrintModules;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void main(String[] args) {
        int arr[]= new int[]{3,2,4,1};
        PrintModules.print(arr);
        quickSort(arr,0,arr.length-1);
        PrintModules.print(arr);
    }

    private static void quickSort(int[] arr, int i, int j) {
        if(i<j) {
            int q = randomPartition(arr, i, j);
            System.out.println("q = "+q);
            quickSort(arr, i, q);
            quickSort(arr, q+1, j);
        }
    }

    private static int randomPartition(int[] arr, int i, int j) {
        int random = i + (new Random().nextInt(j-i+1));
        swap(arr,random,j);
        return partition(arr,i,j);
    }

    private static int partition(int[] arr, int i, int j) {
        int pivot = arr[j];
        int left = i - 1;  // Start before the first element
        for (int k = i; k < j; k++) {  // Only go up to j - 1
            if (arr[k] <= pivot) {  // Move elements <= pivot to left
                left++;
                swap(arr, left, k);
            }
        }
        swap(arr, left + 1, j);  // Swap pivot into correct place
        return left + 1;  // Return the index of the pivot
    }

    private static void swap(int arr[], int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
