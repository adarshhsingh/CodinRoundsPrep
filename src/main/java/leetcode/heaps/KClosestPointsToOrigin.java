package leetcode.heaps;

import reusables.PrintModules;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] p1, int[]p2) {
                int d1 = p1[0] * p1[0] + p1[1] * p1[1];
                int d2 = p2[0] * p2[0] + p2[1] * p2[1];
                return d2 - d1;
            }
        });

        for(int[] p : points) {
            heap.add(new int[]{ p[0], p[1] });
            if(heap.size() > k) {
                heap.remove();
            }
        }

        int[][] result = new int[k][2];
        for(int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{1,3},{-2,2}};
        int k = 1;
        PrintModules.print(new KClosestPointsToOrigin().kClosest(points,k));

        points = new int[][]{{3,3},{5,-1},{-2,4}};
        k = 2;
        PrintModules.print(new KClosestPointsToOrigin().kClosest(points,k));

    }
}
