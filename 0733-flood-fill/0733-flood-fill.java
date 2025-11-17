class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length, m = image[0].length;
        boolean[][] visited = new boolean[n][m];
        int prev = image[sr][sc];

        // Only call DFS if the new color is different
        if (prev != color) {
            dfs(image, visited, sr, sc, prev, color, n, m);
        }

        return image;
    }

    private void dfs(int[][] image, boolean[][] visited, int i, int j, int prev, int color, int n, int m) {
        // Base case
        if (i < 0 || j < 0 || i >= n || j >= m || visited[i][j] || image[i][j] != prev)
            return;

        visited[i][j] = true;
        image[i][j] = color; // fill new color

        // Explore 4 directions
        dfs(image, visited, i - 1, j, prev, color, n, m); // up
        dfs(image, visited, i + 1, j, prev, color, n, m); // down
        dfs(image, visited, i, j - 1, prev, color, n, m); // left
        dfs(image, visited, i, j + 1, prev, color, n, m); // right
    }
}