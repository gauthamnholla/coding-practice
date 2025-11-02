
import java.util.*;

public class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // Step 1: Initialize the grid
        int[][] grid = new int[m][n];
        
        // Mark walls (2) on the grid
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = 2; // Wall
        }

        // Mark guards (3) on the grid
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = 3; // Guard
        }

        // Step 2: Mark guarded cells
        for (int[] guard : guards) {
            int x = guard[0];
            int y = guard[1];

            // Move in all 4 cardinal directions
            // North
            for (int i = x - 1; i >= 0; i--) {
                if (grid[i][y] == 2 || grid[i][y] == 3) break; // Stop at walls or guards
                if (grid[i][y] == 0) grid[i][y] = 1; // Mark as guarded
            }

            // South
            for (int i = x + 1; i < m; i++) {
                if (grid[i][y] == 2 || grid[i][y] == 3) break; // Stop at walls or guards
                if (grid[i][y] == 0) grid[i][y] = 1; // Mark as guarded
            }

            // West
            for (int j = y - 1; j >= 0; j--) {
                if (grid[x][j] == 2 || grid[x][j] == 3) break; // Stop at walls or guards
                if (grid[x][j] == 0) grid[x][j] = 1; // Mark as guarded
            }

            // East
            for (int j = y + 1; j < n; j++) {
                if (grid[x][j] == 2 || grid[x][j] == 3) break; // Stop at walls or guards
                if (grid[x][j] == 0) grid[x][j] = 1; // Mark as guarded
            }
        }

        // Step 3: Count unguarded cells
        int unguardedCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    unguardedCount++; // Count unguarded cells
                }
            }
        }

        return unguardedCount;
    }
}


