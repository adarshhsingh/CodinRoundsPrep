package codingPatterns.mergeIntervals;

import reusables.PrintModules;

import java.util.ArrayList;
import java.util.List;

public class IntervalsListIntersection {

    public static void main(String[] args) {
        int[][] intervalLista = new int[][]{{1,4},{5,6},{7,8},{9,15}};
        int[][] intervalListb = new int[][]{{2,4},{5,7},{9,15}};
        PrintModules.print(intervalsIntersection(intervalLista, intervalListb));
    }
    public static int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {
        int i = 0;
        int j = 0;

        int n = intervalLista.length;
        int m = intervalListb.length;

        List<int[]> intervalsIntersection = new ArrayList<>();

        while(i < n && j < m) {
            // Check if intervals overlap
            int start = Math.max(intervalLista[i][0], intervalListb[j][0]);
            int end = Math.min(intervalLista[i][1], intervalListb[j][1]);

            // If they overlap, add the intersection
            if (start <= end) {
                intervalsIntersection.add(new int[]{start, end});
            }

            // Move the pointer for the interval with the smaller endpoint
            if (intervalLista[i][1] < intervalListb[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return intervalsIntersection.toArray(new int[intervalsIntersection.size()][2]);
    }


}
