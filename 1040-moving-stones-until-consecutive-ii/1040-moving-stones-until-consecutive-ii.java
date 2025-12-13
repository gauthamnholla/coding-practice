import java.util.Arrays;

class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int maxMove = Math.max(stones[n - 1] - stones[1] + 1 - (n - 1),
                               stones[n - 2] - stones[0] + 1 - (n - 1));

        int minMove = n;
        int i = 0;

        for (int j = 0; j < n; ++j) {
            // Expand window [i, j]
            while (stones[j] - stones[i] + 1 > n) i++;
            int alreadyPlaced = j - i + 1;

            // Special case: one gap (like [1,2,3,4,6])
            if (alreadyPlaced == n - 1 && stones[j] - stones[i] + 1 == n - 1)
                minMove = Math.min(minMove, 2);
            else
                minMove = Math.min(minMove, n - alreadyPlaced);
        }

        return new int[]{minMove, maxMove};
    }
}