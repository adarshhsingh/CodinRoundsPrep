package leetcode.HARD;

import reusables.PrintModules;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {
    int distanceFromFriendsHome[][];
    int[][] DIRECTIONS = {{1,0},{0,1},{-1,0},{0,-1}};
    public int shortestDistance(int[][] grid) {
        // start trip from each plot and add the distance of till empty land
        distanceFromFriendsHome = new int[grid.length][grid[0].length];
        int landNotVisitedValue = 0;
        int landVisitedValue = -1;
        System.out.println("** grid **");
        PrintModules.print(grid);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    System.out.println("Starting traversing from location : "+ i + " , "+ j);
                    measureDistanceByBFS(grid, i,  j, landVisitedValue, landNotVisitedValue);
                    --landVisitedValue;
                    --landNotVisitedValue;
                    System.out.println("** grid **");
                    PrintModules.print(grid);
                    System.out.println("** distanceFromFriendsHome **");
                    PrintModules.print(distanceFromFriendsHome);
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        // now return the minimum value from the distanceFromFriendsHome
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(distanceFromFriendsHome[i][j] != 0 && grid[i][j] == (landVisitedValue + 1)) {
                    minDistance = Math.min(minDistance, distanceFromFriendsHome[i][j]);
                }
            }
        }
        return (minDistance == Integer.MAX_VALUE)?-1:minDistance;
    }

    private void measureDistanceByBFS(int[][] grid, int i, int j, int landVisitedValue, int landNotVisitedValue) {
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        queue.add(new int[]{i,j,0});
        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            for(int[] direction : DIRECTIONS) {
                int x = currentPosition[0];
                int y = currentPosition[1];
                int distance = currentPosition[2];

                int newX = x + direction[0];
                int newY = y + direction[1];

                if(newX >= 0 && newY >= 0 && newX < n && newY < m && grid[newX][newY] == landNotVisitedValue) {
                    distanceFromFriendsHome[newX][newY] += (1 + distance);
                    grid[newX][newY] = landVisitedValue;
                    queue.add(new int[]{newX, newY, distance + 1});
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ShortestDistanceFromAllBuildings obj = new ShortestDistanceFromAllBuildings();
        System.out.println(obj.shortestDistance(grid));
        grid = new int[][]{{1,1},{0,1}};
        System.out.println(obj.shortestDistance(grid));
    }
}
