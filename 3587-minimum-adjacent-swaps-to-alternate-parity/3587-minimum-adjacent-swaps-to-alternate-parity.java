class Solution {
        public static int helper(List<Integer> indices) {
        int swaps = 0;
        for (int i = 0; i < indices.size(); i++) {
            swaps += Math.abs(indices.get(i) - 2 * i);
        }
        return swaps;
    }

    public static int minSwaps(int[] nums) {
        List<Integer> evenIndices = new ArrayList<>();
        List<Integer> oddIndices = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                evenIndices.add(i);
            } else {
                oddIndices.add(i);
            }
        }

        int evenCount = evenIndices.size();
        int oddCount = oddIndices.size();

        if (Math.abs(evenCount - oddCount) > 1) {
            return -1;
        }

        int ans = Integer.MAX_VALUE;

        if (evenCount >= oddCount) {
            ans = Math.min(ans, helper(evenIndices));
        }

        if (oddCount >= evenCount) {
            ans = Math.min(ans, helper(oddIndices));
        }

        return ans;
    }
}