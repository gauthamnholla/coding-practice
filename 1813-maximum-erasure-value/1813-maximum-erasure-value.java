import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        long max = 0;
        long curr = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (set.contains(nums[right])) {
                curr -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            curr += nums[right];
            set.add(nums[right]);
            if (curr > max) {
                max = curr;
            }
        }
        return (int) max;
    }
}
