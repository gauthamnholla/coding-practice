import java.util.Arrays;

class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] temp = new int[n];
        
        int mid = (n + 1) / 2; // Middle index
        int j = mid - 1;       // Last index of first half
        int k = n - 1;         // Last index of second half
        
        // Fill from left to right: smaller half in even indices, larger half in odd indices
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                temp[i] = nums[j--];
            } else {
                temp[i] = nums[k--];
            }
        }
        
        // Copy result back to nums
        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
    }
}