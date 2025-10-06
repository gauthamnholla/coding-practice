class Solution {

    private int n; // number of rows in the grid
    private int m; // number of columns in the grid

    // Directions for moving up, right, down, and left
    private static final int[][] directions = {
        {-1, 0},  // up
        {0, 1},   // right
        {1, 0},   // down
        {0, -1}   // left
    };

    // Helper function to check if a cell (i, j) is inside grid bounds
    private boolean isInside(int i, int j) {
        return (i >= 0 && i < n && j >= 0 && j < m);
    }

    /**
     * Depth-First Search (DFS)
     * -------------------------
     * Explore all reachable cells where grid value <= mid.
     * We simulate that at time = mid, we can "swim" through all
     * cells with values ≤ mid (since water level = mid).
     */
    private void dfs(int[][] grid, boolean[][] visited, int mid, int row, int col) {
        visited[row][col] = true; // mark the current cell as visited

        // Explore all 4 directions
        for (int dir = 0; dir < 4; dir++) {
            int i = row + directions[dir][0];
            int j = col + directions[dir][1];

            // Continue DFS if:
            // - cell is inside grid
            // - not visited yet
            // - cell height ≤ mid (water level allows swimming)
            if (isInside(i, j) && !visited[i][j] && grid[i][j] <= mid) {
                dfs(grid, visited, mid, i, j);
            }
        }
    }

    /**
     * Check if it's possible to reach bottom-right cell when water level = mid.
     */
    private boolean possible(int[][] grid, int mid) {
        // If starting cell’s height is greater than current water level, can’t start
        if (grid[0][0] > mid)
            return false;
        
        // Track visited cells
        boolean[][] visited = new boolean[n][m];

        // Start DFS from (0, 0)
        dfs(grid, visited, mid, 0, 0);

        // If we can reach bottom-right cell, return true
        return visited[n - 1][m - 1];
    }

    /**
     * swimInWater()
     * --------------
     * Uses Binary Search + DFS:
     * - Binary search on time (0 to n*n)
     * - For each mid (time), check if it's possible to reach destination
     */
    public int swimInWater(int[][] grid) {
        n = grid.length;   // number of rows
        m = grid[0].length; // number of columns

        int low = 0;        // minimum possible time
        int high = n * n;   // maximum possible time (since heights are from 0 to n² - 1)
        int res = high;     // store the minimum valid time found

        // Binary search over possible times
        while (low <= high) {
            int mid = low + (high - low) / 2; // current water level (time)

            // If possible to reach destination at this water level
            if (possible(grid, mid)) {
                res = mid;       // store result
                high = mid - 1;  // try smaller time (better answer)
            } else {
                low = mid + 1;   // need higher water level
            }
        }

        // Return the minimum time needed to reach bottom-right
        return res;
    }
}
