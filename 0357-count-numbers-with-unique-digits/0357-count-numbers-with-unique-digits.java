class Solution {
    private int ans;

    public int countNumbersWithUniqueDigits(int n) {
        ans = 0;
        backtrack(n, new LinkedList<>());
        return ans;
    }

    private void backtrack(int n, List<Integer> path) {
        ans++;
        if (path.size() == n) {
            return;
        }
        for (int num = 0; num <= 9; num++) {
            if (path.isEmpty() && num == 0) {
                continue;
            }
            boolean allUnique = true;
            for (Integer digit : path) {
                if (num == digit) {
                    allUnique = false;
                    break;
                }
            }
            if (!allUnique) {
                continue;
            }
            path.add(num);
            backtrack(n, path);
            path.removeLast();
        }
    }
}