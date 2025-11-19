import java.util.*;

public class Solution {
    // Union-Find (Disjoint Set Union)
    static class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                parent[rootB] = rootA;
            }
        }
    }

    public int largestComponentSize(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        DSU dsu = new DSU(maxVal + 1);

        for (int num : nums) {
            // Union num with its factors
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    dsu.union(num, i);
                    dsu.union(num, num / i);
                }
            }
        }

        Map<Integer, Integer> componentSizes = new HashMap<>();
        int maxComponentSize = 0;
        for (int num : nums) {
            int root = dsu.find(num);
            int currentSize = componentSizes.getOrDefault(root, 0) + 1;
            componentSizes.put(root, currentSize);
            maxComponentSize = Math.max(maxComponentSize, currentSize);
        }

        return maxComponentSize;
    }
}
