import java.util.*;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<String> result) {
        // If we have 4 parts and we've used all characters, it's a valid IP
        if (path.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", path));
            }
            return;
        }

        // Try all possible segment lengths: 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) break;
            String segment = s.substring(start, start + len);
            if (isValid(segment)) {
                path.add(segment); // Choose
                backtrack(s, start + len, path, result); // Explore
                path.remove(path.size() - 1); // Un-choose
            }
        }
    }

    private boolean isValid(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) return false;
        int val = Integer.parseInt(segment);
        return val >= 0 && val <= 255;
    }
}
