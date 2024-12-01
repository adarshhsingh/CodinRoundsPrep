package datastructures.arrays.julypreperation.easy.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;

public class RankTransformOfAnArray {

    public int[] arrayRankTransform(int[] arr) {
        // edge case if arr is empty
        if(arr.length == 0) return arr;

        // copy arr and sort
        int copyArr[] = arr.clone();
        Arrays.sort(copyArr);

        // create rankMap for unique elements
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for(int i:copyArr) {
            if(!rankMap.containsKey(i)) {
                rankMap.put(i,rank++);
            }
        }

        // replace arr[i] with rank
        for(int i=0;i<arr.length;i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        return arr;
    }

    public static void main(String[] args) {
        int arr[] = {37,12,28,9,100,56,80,5,12};
        for(int i:arr) {
            System.out.print(i+" ");
        }
        System.out.println();
        RankTransformOfAnArray obj = new RankTransformOfAnArray();
        int solution[]= obj.arrayRankTransform(arr);
        for(int i:solution){
            System.out.print(i+" ");
        }
    }
}
