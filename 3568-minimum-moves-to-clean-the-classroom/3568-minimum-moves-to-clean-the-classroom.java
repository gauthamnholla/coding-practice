class Solution {
    int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    //to kepp track of pos of litter
    Map<String, Integer> pos = new HashMap<>();

    int solve(char[][] g, int sX, int sY, int energy, int count) {
        int m = g.length, n = g[0].length;
        int MASK = (1 << count) - 1;
        //dp table for prunning 
        int[][][] dp = new int[m][n][1 << count];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) 
                Arrays.fill(dp[i][j], -1);
        }

        Queue<int[]> q = new LinkedList<>();
        //add initial position to queue
        q.offer(new int[]{sX, sY, energy, 0, 0});
        dp[sX][sY][0] = energy;

        while (!q.isEmpty()) {
            //remove the top element
            int[] top = q.poll();
            if (top[3] == MASK) {
                return top[4];
            }

            for (int[] d : dirs) {
                int newX = top[0] + d[0], newY = top[1] + d[1];
                if (newX < 0 || newY < 0 || newX >= m || newY >= n || g[newX][newY] == 'X')
                    continue;

                int en = top[2] - 1;
                if (en < 0) continue;
                if (g[newX][newY] == 'R') {
                    en = energy;
                }

                int mask = top[3];
                if (g[newX][newY] == 'L') {
                    int ind = pos.get(newX + "," + newY);
                    mask |= (1 << ind);
                }
                //prune with dp table
                if (en <= dp[newX][newY][mask]) {
                    continue;
                }

                dp[newX][newY][mask] = en;
                //add new state to queue
                q.offer(new int[]{newX, newY, en, mask, top[4] + 1});
            }
        }

        return -1;
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] g = new char[m][n];
        int count = 0;
        int sX = -1, sY = -1;
        //tarck the st pos and pos of litter
        for (int i = 0; i < m; i++) {
            g[i] = classroom[i].toCharArray();
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 'L')
                    pos.put(i + "," + j, count++);
                if (g[i][j] == 'S') {
                    sX = i;
                    sY = j;
                }
            }
        }

        return solve(g, sX, sY, energy, count);
    }
}