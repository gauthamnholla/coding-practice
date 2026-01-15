class Solution {
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        long[] sums = new long[nums.length+2];
        int[] other = new int[nums.length+2];

        long max = 0;
        long[] ans = new long[removeQueries.length];
        for (int i = removeQueries.length-1; i > -1; --i) {
            int now = removeQueries[i];
            int left = sums[now] == 0 ? now+1 : other[now];
            int right = sums[now+2] == 0 ? now+1 : other[now+2];
            other[left] = right;
            other[right] = left;

            long sum = sums[left]+sums[right]+nums[now];
            sums[left] = sums[right] = sum;

            ans[i] = max;
            max = Math.max(max, sum);
        }
        return ans;
    }
}