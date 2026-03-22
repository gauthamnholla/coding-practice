class Solution {
        public int minCost(int[] A, int[] B) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int x : A) m.merge(x, 1, Integer::sum);
        for (int x : B) m.merge(x, -1, Integer::sum);
        int res = 0;
        for (int v : m.values()) {
            if (v % 2 != 0) return -1;
            if (v > 0) res += v / 2;
        }
        return res;
    }
}