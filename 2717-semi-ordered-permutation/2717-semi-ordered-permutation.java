class Solution {
    public int semiOrderedPermutation(int[] nums) {
        int n = nums.length;
        int pos1 = -1, posN = -1;

        // Find positions of 1 and n
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) pos1 = i;
            if (nums[i] == n) posN = i;
        }

        // Swaps to bring 1 to front + swaps to bring n to back
        int ops = pos1 + (n - 1 - posN);

        // If 1 is to the RIGHT of n, their paths cross
        // Moving 1 left past n shifts n right by 1 (one less swap needed for n)
        if (pos1 > posN) ops--;

        return ops;
    }
}