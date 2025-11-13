class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0; // Handle empty array

        int i = 1; // Pointer for the position of unique elements

        // Traverse array and copy unique elements forward
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i - 1]) { // Found a new unique element
                nums[i] = nums[j];
                i++;
            }
        }

        return i; // 'i' is the count of unique elements
    }
}
