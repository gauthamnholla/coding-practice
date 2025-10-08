import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // Sort the potions array for binary search
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n]; // Result array to store the count of successful pairs for each spell

        // For each spell, find how many potions can form a successful pair
        for (int i = 0; i < n; ++i) {
            long s = spells[i];

            // Calculate the minimum potion strength needed to reach the 'success' threshold
            // success / s, rounded up -> (success + s - 1) / s
            long need = (success + s - 1) / s;

            // Use binary search to find the first potion with strength >= need
            int pos = lowerBound(potions, need);

            // All potions from 'pos' to the end are valid (successful)
            ans[i] = m - pos;
        }
        return ans;
    }

    // Custom implementation of lowerBound (similar to C++ lower_bound)
    // It returns the smallest index where arr[index] >= target
    private int lowerBound(int[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1; // Avoids overflow, equivalent to (l + r) / 2
            if ((long)arr[mid] < target)
                l = mid + 1; // Move right if potion is too weak
            else
                r = mid; // Move left if potion is strong enough
        }
        return l; // 'l' is the first index with arr[l] >= target
    }
}
