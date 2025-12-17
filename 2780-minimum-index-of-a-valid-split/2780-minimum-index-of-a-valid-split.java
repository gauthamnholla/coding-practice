class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();

        // Step 1: find dominant element using Boyer-Moore
        int cand = 0, freq = 0;
        for (int x : nums) {
            if (freq == 0) cand = x;
            freq += (x == cand) ? 1 : -1;
        }

        // Step 2: total count
        int totalCount = 0;
        for (int x : nums) if (x == cand) totalCount++;

        // Step 3: find split index
        int leftCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) == cand) leftCount++;

            int rightCount = totalCount - leftCount;

            if (leftCount * 2 > (i + 1) &&
                rightCount * 2 > (n - (i + 1)))
                return i;
        }
        return -1;
    }
}
