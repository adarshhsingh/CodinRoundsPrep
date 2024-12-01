package leetcode.HARD;

import reusables.PrintModules;

import java.util.*;

public class BestMeetingPoint {
    int distance[][];
    int[][] DIRECTIONS = {{0,1},{1,0},{0,-1},{-1,0}};
    public int minTotalDistance(int[][] grid) {
        // just go through each house and find distance of each location
        // after going through a empty location once - update visited
        int n = grid.length;
        int m = grid[0].length;
        distance = new int[n][m];
        int visited = -1;

        List<int[]> homeLocations = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    homeLocations.add(new int[]{i,j});
                }
            }
        }
        PrintModules.print(grid);
        //System.out.println("HOME LOCATION: "+ homeLocations.get(0)[0] + " , " +homeLocations.get(0)[1]);

        for(int[] location : homeLocations) {
            System.out.println("CALCULATING DIST. FROM LOCATION: "+ location[0] + ", "+ location[1]);
            calculculateDistanceViaBFS(grid, location[0], location[1], visited);
            System.out.println("*** grid ***");
            PrintModules.print(grid);
            System.out.println("*** distance ***");
            PrintModules.print(distance);
            --visited;
        }

        int minTotalDistanceTravelled = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] != 1) {
                    minTotalDistanceTravelled = Math.min(minTotalDistanceTravelled, distance[i][j]);
                }
            }
        }

        return minTotalDistanceTravelled;
    }

    private void calculculateDistanceViaBFS(int[][] grid, int i, int j, int visited) {
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        queue.add(new int[]{i,j,0});
        grid[i][j] = visited;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            for(int[] direction : DIRECTIONS) {
                int x = curr[0];
                int y = curr[1];
                int level = curr[2];
                int newX = x + direction[0];
                int newY = y + direction[1];
                int newLevel = level + 1;

                if(newX >= 0 && newY >= 0 && newX < n && newY < m && grid[newX][newY] != visited) {
                    grid[newX][newY] = visited;
                    distance[newX][newY] += newLevel;
                    queue.add(new int[]{newX, newY, newLevel});
                }
            }

        }

    }
    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        BestMeetingPoint obj = new BestMeetingPoint();
        System.out.println(obj.minTotalDistance(grid));
    }
}
