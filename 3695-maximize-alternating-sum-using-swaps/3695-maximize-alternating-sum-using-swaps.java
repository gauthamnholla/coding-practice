import java.util.*;

class Solution {
    // Helper class for Disjoint Set Union (DSU) with Path Compression and Union by Size
    private static class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            // Path compression
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                // Union by size
                if (size[rootI] < size[rootJ]) {
                    int temp = rootI;
                    rootI = rootJ;
                    rootJ = temp;
                }
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            }
        }
    }

    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        DSU dsu = new DSU(n);

        // 1. Build connected components from swaps
        for (int[] swap : swaps) {
            dsu.union(swap[0], swap[1]);
        }

        // 2. Group indices and values by their component
        Map<Integer, List<Integer>> componentIndices = new HashMap<>();
        Map<Integer, List<Integer>> componentValues = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentIndices.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
            componentValues.computeIfAbsent(root, k -> new ArrayList<>()).add(nums[i]);
        }

        long totalMaxSum = 0;

        // 3. For each component, apply the greedy strategy
        for (int root : componentIndices.keySet()) {
            List<Integer> indices = componentIndices.get(root);
            List<Integer> values = componentValues.get(root);

            // Count even positions in this component
            int evenCount = 0;
            for (int index : indices) {
                if (index % 2 == 0) {
                    evenCount++;
                }
            }

            // Sort values descending to easily pick the largest
            values.sort(Collections.reverseOrder());

            // Add the largest values (for even slots)
            for (int i = 0; i < evenCount; i++) {
                totalMaxSum += values.get(i);
            }

            // Subtract the remaining smaller values (for odd slots)
            for (int i = evenCount; i < values.size(); i++) {
                totalMaxSum -= values.get(i);
            }
        }

        return totalMaxSum;
    }
}