class Solution {
        public int[][] colorGrid(int n, int m, int[][] sources) {
        List<int[]> q = new ArrayList<>();
        for (int[] s : sources) {
            q.add(new int[]{s[0], s[1], s[2]});
        }
        q.sort((a, b) -> Integer.compare(b[2], a[2]));
        int[][] A = new int[n][m];
        for (int[] s : q) {
            A[s[0]][s[1]] = s[2];
        }
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        for (int k = 0; k < q.size(); k++) {
            int[] curr = q.get(k);
            int i = curr[0], j = curr[1], v = curr[2];
            for (int d = 0; d < 4; d++) {
                int x = i + dx[d], y = j + dy[d];
                if (x >= 0 && x < n && y >= 0 && y < m && A[x][y] == 0) {
                    A[x][y] = v;
                    q.add(new int[]{x, y, v});
                }
            }
        }
        return A;
    }
}