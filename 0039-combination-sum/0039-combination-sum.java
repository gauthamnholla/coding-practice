class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        // Start recursive backtracking
        makeCombination(candidates, target, 0, new ArrayList<>(), 0, res);
        return res;
    }

    // Helper function to build combinations
    private void makeCombination(int[] candidates, int target, int idx,
                                 List<Integer> comb, int total, List<List<Integer>> res) {

        // Base case: found a valid combination
        if (total == target) {
            res.add(new ArrayList<>(comb));
            return;
        }

        // Stop if total exceeds target or out of bounds
        if (total > target || idx >= candidates.length) {
            return;
        }

        // Choose current candidate
        comb.add(candidates[idx]);
        makeCombination(candidates, target, idx, comb, total + candidates[idx], res);

        // Backtrack: remove last and move to next candidate
        comb.remove(comb.size() - 1);
        makeCombination(candidates, target, idx + 1, comb, total, res);
    }
}
