class Solution {
    public List<Integer> maxScoreIndices(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int max = -1;
        int ones = 0;
        int zeros = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) ones++;
        }

        for (int i = 0; i <= nums.length; i++) {
            int sum = zeros + ones;
            if (sum > max) {
                max = sum;
                ans.clear();
                ans.add(i);
            } else if (sum == max) {
                ans.add(i);
            }

            if (i < nums.length) {
                if (nums[i] == 0) {
                    zeros++;
                } else {
                    ones--;
                }
            }
        }
        return ans;
    }
}