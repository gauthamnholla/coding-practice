class Solution {
    public static boolean helper(int grid[][], int r, int c, int n, int expVal) {
        if (r < 0 || r >= n || c < 0 || c >= n || grid[r][c] != expVal) {
            return false;
        }

        if (expVal == n * n - 1)
            return true;

        boolean ans1 = helper(grid, r - 2, c + 1, n, expVal + 1);
        boolean ans2 = helper(grid, r - 2, c - 1, n, expVal + 1);
        boolean ans3 = helper(grid, r - 1, c + 2, n, expVal + 1);
        boolean ans4 = helper(grid, r - 1, c - 2, n, expVal + 1);
        boolean ans5 = helper(grid, r + 2, c + 1, n, expVal + 1);
        boolean ans6 = helper(grid, r + 2, c - 1, n, expVal + 1);
        boolean ans7 = helper(grid, r + 1, c + 2, n, expVal + 1);
        boolean ans8 = helper(grid, r + 1, c - 2, n, expVal + 1);


        return ans1 || ans2 || ans3 || ans4 || ans5 ||ans6 ||ans7 ||ans8;
    }

    public boolean checkValidGrid(int[][] grid) {
        return helper(grid, 0, 0, grid.length, 0);
    }
}