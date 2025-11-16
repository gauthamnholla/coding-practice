class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;

        int i = 0, j = 0, k = 0;

        Arrays.sort(nums1); // Sort first array
        Arrays.sort(nums2); // Sort second array

        // Use two pointers to find matching elements
        while (i < l1 && j < l2) {
            if (nums1[i] < nums2[j]) {
                i++; // Move pointer in nums1
            } 
            else if (nums1[i] > nums2[j]) {
                j++; // Move pointer in nums2
            } 
            else {
                // Match found → store result in nums1
                nums1[k++] = nums1[i];
                i++;
                j++;
            }
        }

        // Return the first k elements (intersection)
        return Arrays.copyOfRange(nums1, 0, k);
    }
}
