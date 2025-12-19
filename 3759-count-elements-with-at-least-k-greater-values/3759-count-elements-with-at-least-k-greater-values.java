import java.util.Arrays;

class Solution {
    public int countElements(int[] nums, int k) {
        if (k == 0) return nums.length;

        Arrays.sort(nums);
        int n = nums.length;

        // k-th largest element
        int threshold = nums[n - k];

        int ans = 0;
        for (int x : nums) {
            if (x < threshold) {
                ans++;
            }
        }

        return ans;
    }
}