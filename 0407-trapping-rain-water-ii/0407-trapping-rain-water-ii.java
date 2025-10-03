class Solution {

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        
        // visited matrix to mark cells already processed
        boolean[][] visited = new boolean[m][n];

        // Min-heap based on height → always expand from the lowest boundary
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Step 1: Push all boundary cells into the priority queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.add(new int[]{heightMap[i][j], i, j}); // {height, row, col}
                    visited[i][j] = true;
                }
            }
        }

        // Directions for exploring neighbors (right, left, down, up)
        int[][] direction = {{0,1}, {0,-1}, {1,0}, {-1,0}};

        int waterVolume = 0; // total trapped water

        // Step 2: BFS traversal using min-heap
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int cv = arr[0]; // Current cell height (boundary level)
            int cr = arr[1]; // Current row
            int cc = arr[2]; // Current column

            // Step 3: Check all 4 neighbors
            for (int[] dir : direction) {
                int nr = cr + dir[0]; // New row
                int nc = cc + dir[1]; // New col

                // If inside grid and not visited
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {

                    // If current boundary is taller → water can be trapped
                    if (cv > heightMap[nr][nc]) {
                        waterVolume += cv - heightMap[nr][nc]; // trapped water
                        pq.add(new int[]{cv, nr, nc}); // push with raised boundary
                    } else {
                        // Otherwise, push neighbor's own height as new boundary
                        pq.add(new int[]{heightMap[nr][nc], nr, nc});
                    }

                    visited[nr][nc] = true; // mark visited
                }
            }
        }

        // Step 4: Return total trapped water
        return waterVolume;
    }
}
