import java.util.*;

class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int totalSum = 0;
        for (int x : nums) totalSum += x;

        // Split array
        int[] left = Arrays.copyOfRange(nums, 0, n);
        int[] right = Arrays.copyOfRange(nums, n, 2 * n);

        // Store subset sums grouped by count
        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        // Generate all subsets for left half
        for (int mask = 0; mask < (1 << n); mask++) {
            int sum = 0, count = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += left[i];
                    count++;
                }
            }
            leftSums.get(count).add(sum);
        }

        // Generate all subsets for right half
        for (int mask = 0; mask < (1 << n); mask++) {
            int sum = 0, count = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += right[i];
                    count++;
                }
            }
            rightSums.get(count).add(sum);
        }

        // Sort right sums for binary search
        for (int i = 0; i <= n; i++) {
            Collections.sort(rightSums.get(i));
        }

        int ans = Integer.MAX_VALUE;

        // Combine left and right
        for (int i = 0; i <= n; i++) {
            List<Integer> lList = leftSums.get(i);
            List<Integer> rList = rightSums.get(n - i);

            for (int lSum : lList) {
                int target = (totalSum / 2) - lSum;

                int idx = Collections.binarySearch(rList, target);

                if (idx >= 0) {
                    int currSum = lSum + rList.get(idx);
                    ans = Math.min(ans, Math.abs(totalSum - 2 * currSum));
                } else {
                    idx = -idx - 1;

                    if (idx < rList.size()) {
                        int currSum = lSum + rList.get(idx);
                        ans = Math.min(ans, Math.abs(totalSum - 2 * currSum));
                    }
                    if (idx > 0) {
                        int currSum = lSum + rList.get(idx - 1);
                        ans = Math.min(ans, Math.abs(totalSum - 2 * currSum));
                    }
                }
            }
        }

        return ans;
    }
}
