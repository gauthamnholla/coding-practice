import java.util.*;

class Solution {
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = Math.abs((long) nums[i]);
        Arrays.sort(arr);

        long cnt = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (r < i) r = i;
            while (r + 1 < n && arr[r + 1] <= 2 * arr[i]) r++;
            cnt += (r - i);
        }
        return cnt;
    }
}