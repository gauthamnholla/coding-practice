class Solution {
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long sum = 0;
        long cur = 1;

        for (int x : nums) {
            if (x < cur) continue;  // skip duplicates or smaller values

            if (x > cur) {
                long end = Math.min(x - 1, cur + k - 1);  // how far we can add
                long count = end - cur + 1;

                // sum of consecutive numbers from cur to end
                sum += (cur + end) * count / 2;

                k -= count;
                if (k == 0) return sum;
            }
            cur = x + 1;
        }

        // If still need more numbers, take from cur onwards
        if (k > 0) {
            long end = cur + k - 1;
            sum += (cur + end) * k / 2;
        }

        return sum;
    }
}
