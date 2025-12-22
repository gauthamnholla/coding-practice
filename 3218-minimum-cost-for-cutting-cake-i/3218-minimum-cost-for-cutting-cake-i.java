class Solution {
        public int minimumCost(int m, int n, int[] h, int[] v) {
        int res = Arrays.stream(h).sum() + Arrays.stream(v).sum();
        for (int a : h)
            for (int b : v)
                res += Math.min(a, b);
        return res;
    }
}