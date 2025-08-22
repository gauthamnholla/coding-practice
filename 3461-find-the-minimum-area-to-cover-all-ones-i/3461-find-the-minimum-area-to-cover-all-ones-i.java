class Solution {
    public int minimumArea(int[][] grid) {
        // Initialize row boundaries
        int minRow = grid.length, maxRow = -1;
        // Initialize column boundaries
        int minCol = grid[0].length, maxCol = -1;

        // Traverse the entire grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // If a '1' is found (part of the object/region)
                if (grid[i][j] == 1) {
                    // Update the min/max row and column boundaries
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        // Calculate the area of the rectangle
        // (height = maxRow - minRow + 1, width = maxCol - minCol + 1)
        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }
}
