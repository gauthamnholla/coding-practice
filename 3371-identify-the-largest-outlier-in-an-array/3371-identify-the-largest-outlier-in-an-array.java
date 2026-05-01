class Solution {
        public int getLargestOutlier(int[] A) {
        Map<Integer, Integer> count = new HashMap<>();
        int total = 0, res = Integer.MIN_VALUE;
        for (int a : A) {
            total += a;
            count.put(a, count.getOrDefault(a, 0) + 1);
        }
        for (int a : A) {
            int outlier = total - a - a;
            if (count.getOrDefault(outlier, 0) > (outlier == a ? 1 : 0)) {
                res = Math.max(res, outlier);
            }
        }
        return res;
    }
}