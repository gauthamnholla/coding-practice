class Solution {
    public int[] shortestToChar(String s, char c) {
        // Get all indices where target character appears
        List<Integer> targetIndex = getIndexOfTarget(s, c);

        int[] ans = new int[s.length()];

        // For each position, find min distance to any target index
        for (int i = 0; i < s.length(); i++) {
            int result = Integer.MAX_VALUE;
            for (int idx : targetIndex) {
                result = Math.min(result, Math.abs(i - idx));
            }
            ans[i] = result;
        }
        return ans;
    }

    // Helper: collect all indexes of character c
    List<Integer> getIndexOfTarget(String s, char c) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) indexes.add(i);
        }
        return indexes;
    }
}
