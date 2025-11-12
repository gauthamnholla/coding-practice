import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        int[] need = new int[128]; // frequency of chars required from t (ASCII)
        for (char c : t.toCharArray()) need[c]++;

        int remaining = t.length();       // number of chars still needed
        int left = 0;
        int bestL = 0, bestR = Integer.MAX_VALUE; // best window [bestL, bestR]

        for (int right = 0; right < s.length(); right++) {
            char rc = s.charAt(right);
            // If this char is required (>0), we cover one required char
            if (need[rc] > 0) remaining--;
            // Consume this char from need (can go negative for excess chars)
            need[rc]--;

            // When we covered all required chars, try to shrink from left
            while (remaining == 0) {
                // Update best window
                if (right - left < bestR - bestL) {
                    bestL = left;
                    bestR = right;
                }

                char lc = s.charAt(left);
                // Release left char back to need
                need[lc]++;
                // If after releasing a required char becomes positive, we need it again
                if (need[lc] > 0) remaining++;
                left++;
            }
        }

        return bestR == Integer.MAX_VALUE ? "" : s.substring(bestL, bestR + 1);
    }
}
