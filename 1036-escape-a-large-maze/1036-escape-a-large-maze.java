class Solution {
    Set<String> blockedSet = new HashSet<>();
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    final int LIMIT = 200;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (int[] b : blocked)
            blockedSet.add(b[0] + "," + b[1]);

        int maxArea = blocked.length * blocked.length;
        return bfs(source, target, maxArea) && bfs(target, source, maxArea);
    }

    private boolean bfs(int[] start, int[] end, int maxArea) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start[0] + "," + start[1]);

        int count = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == end[0] && curr[1] == end[1]) return true;
            if (++count > maxArea) return true;

            for (int[] dir : dirs) {
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                String pos = nx + "," + ny;
                if (nx >= 0 && ny >= 0 && nx < 1e6 && ny < 1e6 && 
                    !blockedSet.contains(pos) && !visited.contains(pos)) {
                    queue.offer(new int[]{nx, ny});
                    visited.add(pos);
                }
            }
        }

        return false;
    }
}