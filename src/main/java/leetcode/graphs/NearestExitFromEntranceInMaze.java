package leetcode.graphs;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

class triplet {
    int x;
    int y;
    int d;

    public triplet(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.d = distance;
    }
}

public class NearestExitFromEntranceInMaze {

    public static void main(String[] args) {
        NearestExitFromEntranceInMaze obj = new NearestExitFromEntranceInMaze();
        /**char maze[][] = {{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        int[] entrance = {1,2};
        int min = obj.nearestExit(maze,entrance);
        System.out.println("\n nearest exit = " + min);
        /**char maze2[][] = {{'.','+','+','+','+'},{'.','+','.','.','.'},{'.','+','.','+','.'},{'.','.','.','+','.'},{'+','+','+','+','.'}};
        int [] entrance = new int[]{0,0};
        int min = obj.nearestExit(maze2,entrance);
        System.out.println("\n nearest exit = " + min);
        char maze3[][] = {{'+','.','+','+','+','+','+'},{'+','.','+','.','.','.','+'},{'+','.','+','.','+','.','+'},{'+','.','.','.','+','.','+'},{'+','+','+','+','+','.','+'}};
        entrance = new int[]{0,1};
        min = obj.nearestExit(maze3,entrance);
        System.out.println("\n nearest exit = " + min);*/
        char maze4[][] = {{'+','.','.','.','.','.','.','+','.','.','.','+','+','.','+','.','+','+','+','.'},{'.','.','.','.','+','.','+','.','+','.','.','.','.','+','.','+','.','.','.','+'},{'.','.','.','.','.','.','.','.','.','+','+','.','+','.','.','.','+','+','.','+'},{'.','.','.','.','+','+','.','+','.','.','.','.','.','+','.','+','.','.','.','.'},{'.','+','+','.','.','.','+','.','.','.','.','.','.','+','.','.','+','.','.','.'},{'.','.','.','+','.','.','.','+','.','.','.','.','.','.','+','+','.','.','.','+'},{'+','.','+','.','+','.','.','+','.','.','.','.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','+','.','.','.','+','+','.','.','.','.','.','+','.','.'},{'.','+','+','+','.','.','.','+','.','.','+','+','+','+','.','.','+','.','+','.'},{'+','.','.','.','+','.','.','.','+','.','.','.','.','+','.','.','+','+','+','.'},{'.','.','.','.','.','+','.','.','.','.','+','.','.','+','.','.','.','.','.','.'},{'+','.','.','.','.','.','.','.','.','.','.','+','.','.','+','.','.','+','.','+'},{'.','.','.','.','+','+','.','+','+','.','.','.','+','.','+','.','.','+','+','.'},{'.','.','.','.','.','.','.','.','.','+','.','.','.','+','.','+','.','.','.','.'},{'+','.','.','.','.','.','.','.','.','.','.','.','.','+','.','+','.','.','.','.'},{'.','+','.','.','.','.','.','.','+','+','.','.','.','.','.','+','+','.','+','+'},{'.','.','+','.','+','.','.','.','+','.','+','.','.','.','.','.','+','.','.','+'},{'.','+','.','+','+','+','.','.','.','.','+','+','.','.','+','.','+','.','.','.'}};
        int[] entrance = new int[]{17,18};
        int min = obj.nearestExit(maze4,entrance);
        System.out.println("\n nearest exit = " + min);
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        // use bfs to find distance from entrance to all reachabale exits

        int xAxis = maze[0].length;
        int yAxis = maze.length;

        int distance[][] = new int[yAxis][xAxis];

        for(int i=0; i<yAxis; i++) {
            for(int j=0; j<xAxis; j++) {
                if(maze[i][j]=='+') {
                    distance[i][j] = -1;
                } else if(i==entrance[0] && j==entrance[1]){
                    distance[i][j] = -1;
                } else {
                    distance[i][j] = 0;
                }
            }
        }

        for(int i=0; i<yAxis; i++) {
            System.out.println();
            for(int j=0; j<xAxis; j++) {
                if(maze[i][j]=='+') {
                    System.out.print(" + ");
                } else {
                    System.out.print(" "+distance[i][j]+" ");
                }
            }
        }

        Queue<triplet> queue = new LinkedList<>();
        queue.add(new triplet(entrance[1], entrance[0], 0));

        int minDistanceExit = Integer.MAX_VALUE;
        Point entry = new Point(entrance[1], entrance[0]);
        // start bfs and update distance
        while(!queue.isEmpty()) {
            triplet currentPosition = queue.remove();
            System.out.printf("at position  y = %d, x = %d and distance = %d \n", currentPosition.y, currentPosition.x, currentPosition.d);

            if (!(currentPosition.x == entrance[1] && currentPosition.y == entrance[0]) && (currentPosition.x == 0 || currentPosition.y == 0 || currentPosition.x == xAxis - 1 || currentPosition.y == yAxis - 1)) {
                System.out.printf("exit found at y = %d, x = %d , distance = %d \n", currentPosition.y, currentPosition.x, currentPosition.d);
                minDistanceExit = Math.min(minDistanceExit, currentPosition.d);
            }

            if (currentPosition.x - 1 >= 0 && distance[currentPosition.y][currentPosition.x - 1] == 0) {
                queue.add(new triplet(currentPosition.x - 1, currentPosition.y, currentPosition.d + 1));
                distance[currentPosition.y][currentPosition.x-1] = currentPosition.d+1;
            }
            if (currentPosition.x + 1 < xAxis && distance[currentPosition.y][currentPosition.x + 1] == 0) {
                queue.add(new triplet(currentPosition.x + 1, currentPosition.y, currentPosition.d + 1));
                distance[currentPosition.y][currentPosition.x+1] = currentPosition.d+1;
            }
            if (currentPosition.y - 1 >= 0 && distance[currentPosition.y - 1][currentPosition.x] == 0) {
                queue.add(new triplet(currentPosition.x, currentPosition.y - 1, currentPosition.d + 1));
                distance[currentPosition.y-1][currentPosition.x] = currentPosition.d+1;
            }
            if (currentPosition.y + 1 < yAxis && distance[currentPosition.y + 1][currentPosition.x] == 0) {
                queue.add(new triplet(currentPosition.x, currentPosition.y + 1, currentPosition.d + 1));
                distance[currentPosition.y+1][currentPosition.x] = currentPosition.d+1;
            }

        }

        if(minDistanceExit == Integer.MAX_VALUE) return -1;

        for(int i=0; i<yAxis; i++) {
            System.out.println();
            for(int j=0; j<xAxis; j++) {
                if(maze[i][j]=='+') {
                    System.out.print("  +  ");
                } else if(i==entrance[0] && j==entrance[1]){
                    System.out.print("  A  ");
                } else {
                    System.out.print(" "+distance[i][j]+" ");
                }
            }
        }

        return minDistanceExit;

    }
}
