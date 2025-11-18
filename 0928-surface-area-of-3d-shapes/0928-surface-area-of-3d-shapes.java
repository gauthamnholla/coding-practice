class Solution {
    public int surfaceArea(int[][] grid) {
        int result = 0;

        // Check each cell and add its contribution to total surface area
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                result += getArea(grid, i, j);

        return result;
    }

    private int getArea(int[][] grid, int i, int j) {
        int towerHeight = grid[i][j];

        // No cubes → no surface area contributed
        if (towerHeight == 0) return 0;

        // Base exposed area for a stack of cubes:
        // Top + Bottom = 2
        // Each cube has 4 sides, but stacked cubes hide touching sides:
        // Total side area = height * 4
        int area = 2 + (towerHeight * 4);

        // Subtract overlapping sides with neighbors
        // Each shared face = min(heightA, heightB)

        if (i > 0)                       // Top neighbor
            area -= Math.min(towerHeight, grid[i - 1][j]);

        if (i < grid.length - 1)         // Bottom neighbor
            area -= Math.min(towerHeight, grid[i + 1][j]);

        if (j > 0)                       // Left neighbor
            area -= Math.min(towerHeight, grid[i][j - 1]);

        if (j < grid[0].length - 1)      // Right neighbor
            area -= Math.min(towerHeight, grid[i][j + 1]);

        return area;
    }
}
