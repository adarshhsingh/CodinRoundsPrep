package leetcode.HARD;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland827 {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int maxSize = 0;
        int islandID = 2; // Start island IDs from 2 to avoid confusion with 0s and 1s
        Map<Integer, Integer> islandSizes = new HashMap<>(); // Map to store island sizes by ID

        // Step 1: Precompute island sizes and assign unique IDs to each island
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                   int size = dfs(grid, i, j, islandID);
                   islandSizes.put(islandID, size);
                   maxSize = Math.max(maxSize, size); // track the largest island size found
                   islandID++;
                }
            }
        }

        // Step 2: Try converting each 0 to 1 and recalculate the potential largest island
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    Set<Integer> neighboringIslands = new HashSet<>();
                    int potentialSize = 1; // start wth 1 for the new cell being turned into land

                    // check all four neighbors
                    if(i > 0) neighboringIslands.add(grid[i - 1][j]);
                    if(i < n - 1) neighboringIslands.add(grid[i + 1][j]);
                    if(j > 0) neighboringIslands.add(grid[i][j - 1]);
                    if(j < n - 1) neighboringIslands.add(grid[i][j + 1]);

                    // sum up all the sizes of unique neighboring islands
                    for(int id: neighboringIslands) {
                        potentialSize += islandSizes.getOrDefault(id, 0);
                    }

                    maxSize = Math.max(maxSize, potentialSize);
                }
            }
        }
        return maxSize;
    }

    private int dfs(int[][] grid, int i, int j, int islandId) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return 0; // base case
        }
        grid[i][j] = islandId;
        return 1 + dfs(grid, i-1, j, islandId) + dfs(grid, i + 1, j, islandId)
                + dfs(grid, i, j - 1, islandId) + dfs(grid, i , j + 1, islandId);
    }


    public static void main(String[] args) {
        MakingALargeIsland827 obj = new MakingALargeIsland827();
        int grid[][] = new int[][] {{1,0},{0,1}};
        System.out.println(obj.largestIsland(grid));
        grid = new int[][] {{1,1},{0,1}};
        System.out.println(obj.largestIsland(grid));
        grid = new int[][] {{1,1},{1,1}};
        System.out.println(obj.largestIsland(grid));
    }
}
