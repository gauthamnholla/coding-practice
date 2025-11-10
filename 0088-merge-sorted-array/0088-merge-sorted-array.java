class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step 1: Copy all elements from nums2 into nums1 (after the first m elements)
        for (int j = 0, i = m; j < n; j++) {
            nums1[i] = nums2[j];
            i++;
        }

        // Step 2: Sort the merged array
        Arrays.sort(nums1);
    }
}
