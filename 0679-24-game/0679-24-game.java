public class Solution {
    private static final double TARGET = 24.0;
    private static final double EPS = 1e-6;

    public boolean judgePoint24(int[] cards) {
        double[] nums = new double[cards.length];
        for (int i = 0; i < cards.length; i++) nums[i] = cards[i];
        return backtrack(nums);
    }

    private boolean backtrack(double[] nums) {
        int n = nums.length;

        // If only one number left, check if close to 24
        if (n == 1) {
            return Math.abs(nums[0] - TARGET) < EPS;
        }

        // Try all ordered pairs (i, j), i != j
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                // Build the list of remaining numbers (excluding i and j)
                double[] next = new double[n - 1];
                int idx = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) next[idx++] = nums[k];
                }

                // Try all possible results combining nums[i] and nums[j]
                for (double val : combine(nums[i], nums[j])) {
                    next[next.length - 1] = val; // place result as the last element
                    if (backtrack(next)) return true;
                }
            }
        }
        return false;
    }

    private double[] combine(double a, double b) {
        // Return all possible results of combining a and b
        // Note: Avoid invalid divisions
        // We include a-b, b-a, a/b, b/a to capture non-commutativity
        java.util.ArrayList<Double> res = new java.util.ArrayList<>(6);
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a * b);
        if (Math.abs(b) > EPS) res.add(a / b);
        if (Math.abs(a) > EPS) res.add(b / a);

        // Convert to array
        double[] out = new double[res.size()];
        for (int i = 0; i < res.size(); i++) out[i] = res.get(i);
        return out;
    }
}
