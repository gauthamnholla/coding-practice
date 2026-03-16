class Solution {
        public int longestEqualSubarray(List<Integer> A, int k) {
        int maxf = 0, i = 0, n = A.size();
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < n; j++) {
            count.put(A.get(j), count.getOrDefault(A.get(j), 0) + 1);
            maxf = Math.max(maxf, count.get(A.get(j)));
            if (j - i + 1 - maxf > k) {
                count.put(A.get(i), count.get(A.get(i)) - 1);
                i++;
            }
        }
        return maxf;
    }
}