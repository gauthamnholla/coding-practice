import java.util.*;

class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] height = new int[m][n];
        for (int[] row : height) Arrays.fill(row, -1);

        Deque<int[]> q = new ArrayDeque<>();

        // initialize queue with all water cells (height 0)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    q.add(new int[] { i, j });
                }
            }
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (height[nr][nc] != -1) continue; // already set
                height[nr][nc] = height[r][c] + 1;
                q.add(new int[] { nr, nc });
            }
        }

        return height;
    }
}

