import java.util.*;

public class Solution {
    Map<String, Double> memo = new HashMap<>();

    public double soupServings(int n) {
        if (n >= 4800) return 1.0; // For large n, answer approaches 1
        int N = (n + 24) / 25;     // Units of 25 mL

        return dfs(N, N);
    }

    private double dfs(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;
        String key = a + "," + b;
        if (memo.containsKey(key))
            return memo.get(key);

        double res = 0.25 * (
            dfs(a - 4, b) +
            dfs(a - 3, b - 1) +
            dfs(a - 2, b - 2) +
            dfs(a - 1, b - 3)
        );
        memo.put(key, res);
        return res;
    }
}
