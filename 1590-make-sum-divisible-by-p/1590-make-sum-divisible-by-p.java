import java.util.*;

class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long total = 0;
        for (int x : nums) total += x;
        int need = (int)(total % p);
        if (need == 0) return 0; // already divisible

        Map<Integer, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0, -1); // prefix 0 seen at index -1

        int ans = n; // initialize as impossible large (cannot remove whole array)
        int prefix = 0; // prefix sum modulo p

        for (int i = 0; i < n; i++) {
            prefix = (int)((prefix + (long)nums[i]) % p);
            // want previous prefix 'want' such that (prefix - want) % p == need
            // rearrange: want == (prefix - need) mod p
            int want = prefix - need;
            if (want < 0) want += p;
            // check if 'want' was seen before
            if (lastIndex.containsKey(want)) {
                int prevIdx = lastIndex.get(want);
                ans = Math.min(ans, i - prevIdx);
            }
            // store/update last occurrence of current prefix
            lastIndex.put(prefix, i);
        }

        if (ans == n) return -1;
        return ans;
    }
}
