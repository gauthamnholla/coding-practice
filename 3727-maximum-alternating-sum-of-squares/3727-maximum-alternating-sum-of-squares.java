class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        // Find min and max absolute values
        int min = 40000, max = 0;
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(nums[i]);
            min = Math.min(min, abs);
            max = Math.max(max, abs);
        }

        // Counting sort by absolute value
        int[] cnt = new int[max - min + 1];
        for (int i = 0; i < n; i++)
            cnt[Math.abs(nums[i]) - min]++;

        int half = (n + 1) / 2;
        long res = 0;
        int proc = 0;

        // Process from largest to smallest
        for (int i = max; i >= min; i--) {
            long sq = (long) i * i;

            for (int j = 0; j < cnt[i - min]; j++) {
                if (proc < half) {
                    res += sq;
                } else {
                    res -= sq;
                }
                proc++;
            }
        }

        return res;
    }
}