class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> curr, List<List<Integer>> res) {
        // Base case: all elements used → add to result
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip already used elements
            if (used[i]) continue;

            // Skip duplicates (only first occurrence allowed)
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // Choose
            used[i] = true;
            curr.add(nums[i]);

            // Explore
            backtrack(nums, used, curr, res);

            // Backtrack
            used[i] = false;
            curr.remove(curr.size() - 1);
        }
    }
}
