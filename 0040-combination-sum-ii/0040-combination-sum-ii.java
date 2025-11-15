class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // Sort to handle duplicates and pruning
        List<List<Integer>> res = new ArrayList<>();

        // Start backtracking
        dfs(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int target, int start, List<Integer> comb, List<List<Integer>> res) {
        // Base case: target exceeded
        if (target < 0) return;

        // Base case: target reached
        if (target == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }

        // Explore candidates starting from 'start'
        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates in same recursive level
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            // Stop early if number is greater than target
            if (candidates[i] > target) break;

            // Choose current number
            comb.add(candidates[i]);
            // Move to next index (each number used once)
            dfs(candidates, target - candidates[i], i + 1, comb, res);
            // Backtrack
            comb.remove(comb.size() - 1);
        }
    }
}
