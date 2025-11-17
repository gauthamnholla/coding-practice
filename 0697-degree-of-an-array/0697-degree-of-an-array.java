class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();

        // Count frequency of each number
        for (int x : nums)
            mp.put(x, mp.getOrDefault(x, 0) + 1);

        // Find the degree of the array (max frequency)
        int f = 0;
        for (int freq : mp.values())
            f = Math.max(f, freq);

        int ans = Integer.MAX_VALUE;

        // For each number with frequency = degree, find shortest subarray covering all its occurrences
        for (int key : mp.keySet()) {
            if (mp.get(key) == f) {

                // Find first occurrence
                int l = 0, r = nums.length - 1;
                while (l <= r && nums[l] != key) l++;

                // Find last occurrence
                while (l < r && nums[r] != key) r--;

                // Update minimum window
                ans = Math.min(ans, r - l + 1);
            }
        }

        return ans;
    }
}
