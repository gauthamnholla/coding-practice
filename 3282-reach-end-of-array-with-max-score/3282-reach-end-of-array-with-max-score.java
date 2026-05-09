class Solution {
        public long findMaximumScore(List<Integer> A) {
        long res = 0, ma = 0;
        for (int a: A) {
            res += ma;
            ma = Math.max(ma, a);
        }
        return res;
    }
}