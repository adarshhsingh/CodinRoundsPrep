package datastructures.arrays.old;

import reusables.PrintModules;

import java.util.List;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 *
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 *  Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class RotateImage {

    public static void printArray(int [][]list) {
        int n = list.length;
        for (int i=0; i < n; i++) {
            if(i==0) System.out.print("[");
            for ( int j = 0; j < n; j++ ) {
                if(j==0) System.out.print("[");
                System.out.print(list[i][j]);
                if (j != n - 1) {
                    System.out.print(",");
                } else {
                    System.out.print("]");
                }
            }
            if (i != n - 1) {
                System.out.print(",");
            } else {
                System.out.print("]");
            }
        }
        System.out.print("]");
    }

    public static void main(String[] args) throws InterruptedException {
        //int arr[][] = new int[][]{{1,2},{3,4}};
        //int arr[][] = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int arr[][] = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        //int arr[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        //PrintModules.print(arr);
        rotate90(arr);
    }

    private static void rotateByTransposeAndReverse(int [][] matrix) {
        // transpose
        int n = matrix.length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    private static void rotate90(int[][] matrix) throws InterruptedException {
        int n = matrix.length;
        for (int i=0; i<n/2; i++) {
            for( int j = i; j<n-i-1; j++) {
                int rotateI = j;
                int rotateJ = n - i - 1;
                int temp;
                while (!(rotateJ == j && rotateI == i)) {
                    temp = matrix[rotateI][rotateJ];
                    matrix[rotateI][rotateJ] = matrix[i][j];
                    matrix[i][j] = temp;
                    temp = rotateJ;
                    rotateJ = n - rotateI - 1;
                    rotateI = temp;
                }
            }
        }

        // from here, it's just for printing in expected format
        for (int i=0; i < n; i++) {
            if(i==0) System.out.print("[");
            for ( int j = 0; j < n; j++ ) {
                if(j==0) System.out.print("[");
                System.out.print(matrix[i][j]);
                if (j != n - 1) System.out.print(",");
                else System.out.print("]");
            }
            if (i != n - 1) System.out.print(",");
            else System.out.print("]");
        }
    }
}
