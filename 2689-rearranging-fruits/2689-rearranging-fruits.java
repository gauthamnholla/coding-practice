import java.util.*;

public class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        // Count the frequency difference of each fruit between the two baskets
        Map<Integer, Integer> freq = new HashMap<>();
        int n = basket1.length;
        int minFruit = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            freq.put(basket1[i], freq.getOrDefault(basket1[i], 0) + 1);
            freq.put(basket2[i], freq.getOrDefault(basket2[i], 0) - 1);
            minFruit = Math.min(minFruit, Math.min(basket1[i], basket2[i]));
        }

        List<Integer> diffs = new ArrayList<>();

        // Build the list of excess fruits to be swapped
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int fruit = entry.getKey();
            int count = entry.getValue();
            // If difference is odd, impossible to balance
            if (Math.abs(count) % 2 != 0) return -1;

            // Only need to consider one basket's excess
            for (int i = 0; i < Math.abs(count) / 2; i++) {
                diffs.add(fruit);
            }
        }

        // No swaps needed if already balanced
        if (diffs.size() == 0) return 0;
        
        // Sort to try smallest costs first
        Collections.sort(diffs);
        long cost = 0;
        // Each swap fixes TWO
        int swaps = diffs.size() / 2;

        for (int i = 0; i < swaps; i++) {
            // Minimum between swapping directly or via two min-froots
            cost += Math.min(diffs.get(i), 2 * minFruit);
        }
        return cost;
    }
}
