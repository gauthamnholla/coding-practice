class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        rightMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        for (int j = 1; j < n - 1; j++) {
            if (leftMin[j - 1] < nums[j] && rightMin[j + 1] < nums[j]) {
                ans = Math.min(ans,
                        leftMin[j - 1] + nums[j] + rightMin[j + 1]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}