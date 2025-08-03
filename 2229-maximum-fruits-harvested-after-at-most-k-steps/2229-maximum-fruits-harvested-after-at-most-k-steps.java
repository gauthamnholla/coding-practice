public class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        // Prefix sum for fruit amounts: fruits[i][0]: position, fruits[i][1]: amount
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i+1] = preSum[i] + fruits[i][1];
        }
        int ans = 0, left = 0;

        // For each right index, find the minimal left such that interval [left, right] can be harvested within k steps.
        for (int right = 0; right < n; ++right) {
            // Keep moving left up as long as it's too far apart
            while (left <= right) {
                int lPos = fruits[left][0], rPos = fruits[right][0];
                // Two movement options:
                int minSteps = Math.min(
                    Math.abs(startPos - lPos) + (rPos - lPos),
                    Math.abs(startPos - rPos) + (rPos - lPos)
                );
                if (minSteps <= k) break;
                left++;
            }
            // Interval [left, right] is valid
            int totalFruits = preSum[right + 1] - preSum[left];
            ans = Math.max(ans, totalFruits);
        }
        return ans;
    }
}
