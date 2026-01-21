class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int slow = i, fast = i;
            boolean forward = nums[i] > 0;

            while (true) {
                slow = next(nums, slow);
                if ((nums[slow] > 0) != forward) break;

                fast = next(nums, fast);
                if ((nums[fast] > 0) != forward) break;

               fast = next(nums, fast); 
                if ((nums[fast] > 0) != forward) break;

                if (slow == fast) {
                    if (slow == next(nums, slow)) break;
                    return true;
                }
            }
        }
        return false;
    }

    private int next(int[] nums, int i) {
        int n = nums.length;
        int next = (i + nums[i]) % n;
        if (next < 0) next += n;
        return next;
    }
}