class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = -10000000000L;  // Minimum possible product
        long right = 10000000000L;  // Maximum possible product

        // Binary search for the k-th smallest product
        while (left < right) {
            long mid = left + (right - left) / 2;

            // Count how many products are <= mid
            if (countProducts(nums1, nums2, mid) < k)
                left = mid + 1;  // Need larger values
            else
                right = mid;     // Search smaller range
        }

        return left; // The k-th smallest product
    }

    // Count how many pairs (a,b) have a*b <= target
    private long countProducts(int[] nums1, int[] nums2, long target) {
        long count = 0;

        for (int num1 : nums1) {
            // If num1 is 0, handle separately
            if (num1 == 0) {
                if (target >= 0) count += nums2.length;
                continue;
            }

            int low = 0, high = nums2.length;
            // Binary search to find valid range in nums2
            while (low < high) {
                int mid = low + (high - low) / 2;
                long product = (long) num1 * nums2[mid];

                if (product <= target) {
                    if (num1 > 0) low = mid + 1;
                    else high = mid;
                } else {
                    if (num1 > 0) high = mid;
                    else low = mid + 1;
                }
            }

            // Add count based on sign of num1
            count += (num1 > 0) ? low : nums2.length - low;
        }

        return count;
    }
}
