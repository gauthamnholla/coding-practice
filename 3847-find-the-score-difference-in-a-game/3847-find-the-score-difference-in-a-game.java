class Solution {
    public int scoreDifference(int[] nums) {
        int player = 0;
        int[] scores = new int[]{0, 0};
        for (int game = 1; game <= nums.length; ++game) {
            player ^= ((nums[game - 1] % 2) ^ ((game % 6 == 0) ? 1 : 0));
            scores[player] += nums[game - 1];
        }
        return scores[0] - scores[1];
    }
}