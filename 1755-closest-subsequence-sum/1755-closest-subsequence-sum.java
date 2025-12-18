import java.util.*;

class Solution {
    int[] arr;

    public int minAbsDifference(int[] nums, int goal) {
        arr = nums;
        int n = nums.length;

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        generate(0, n/2, 0, first);
        generate(n/2, n, 0, second);

        Collections.sort(first);

        int ans = Integer.MAX_VALUE;

        for (int secondSetSum : second) {
            int left = goal - secondSetSum;

            // case: smallest of first is already > left
            if (first.get(0) > left) {
                ans = Math.min(ans, Math.abs(first.get(0) + secondSetSum - goal));
                continue;
            }

            // case: largest of first is already < left
            if (first.get(first.size() - 1) < left) {
                ans = Math.min(ans, Math.abs(first.get(first.size() - 1) + secondSetSum - goal));
                continue;
            }

            int pos = Collections.binarySearch(first, left);
            if (pos >= 0) {
                return 0; // exact match found
            } else {
                pos = -pos - 1; // insertion point
            }

            int low = pos - 1;
            ans = Math.min(ans, Math.abs(first.get(low) + secondSetSum - goal));
            ans = Math.min(ans, Math.abs(first.get(pos) + secondSetSum - goal));
        }

        return ans;
    }

    void generate(int i, int end, int sum, List<Integer> list) {
        if (i == end) {
            list.add(sum);
            return;
        }
        generate(i + 1, end, sum + arr[i], list);
        generate(i + 1, end, sum, list);
    }
}
