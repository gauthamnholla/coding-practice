class Solution {
    public int jump(int[] nums) {
        int near = 0, far = 0, jumps = 0;

        // Continue until the farthest range reaches the end
        while (far < nums.length - 1) {
            int farthest = 0;

            // Find the farthest point reachable in the current jump range
            for (int i = near; i <= far; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }

            // Move to the next range
            near = far + 1;
            far = farthest;
            jumps++; // Increment jump count
        }

        return jumps; // Minimum number of jumps to reach the end
    }
}
