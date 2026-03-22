import java.util.*;

class Solution {
    public int longestArithmetic(int[] nums) {

        int n = nums.length;
        if (n <= 2) return n;

        int ans = 2;
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(left, 2);
        Arrays.fill(right, 2);

        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = n - 3; i >= 0; i--) {
            if (nums[i + 2] - nums[i + 1] == nums[i + 1] - nums[i]) {
                right[i] = right[i + 1] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, Math.max(left[i], right[i]));

            if (i == 0) {
                ans = Math.max(ans, 1 + right[i + 1]);
            }
            else if (i == n - 1) {
                ans = Math.max(ans, 1 + left[i - 1]);
            }
            else {
                ans = Math.max(ans, 1 + left[i - 1]);
                ans = Math.max(ans, 1 + right[i + 1]);
                int diff = nums[i + 1] - nums[i - 1];

                if (diff % 2 == 0) {
                    int req = diff / 2;
                    int leftLen = 1;
                    int rightLen = 1;

                    if (i >= 2 && nums[i - 1] - nums[i - 2] == req) {
                        leftLen = left[i - 1];
                    }
                    if (i < n - 2 && nums[i + 2] - nums[i + 1] == req) {
                        rightLen = right[i + 1];
                    }

                    ans = Math.max(ans, 1 + leftLen + rightLen);
                }
            }
        }

        return ans;
    }
}