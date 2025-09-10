import java.util.*;

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // Set of users who must learn a new language to communicate
        Set<Integer> need = new HashSet<>();
        
        // Step 1: Check each friendship pair
        for (int[] p : friendships) {
            int u = p[0] - 1, v = p[1] - 1; // Convert to 0-based index
            boolean ok = false;

            // Check if u and v share at least one common language
            for (int x : languages[u]) {
                for (int y : languages[v]) {
                    if (x == y) {
                        ok = true;
                        break;
                    }
                }
                if (ok) break;
            }

            // If no common language, mark both users as needing to learn
            if (!ok) {
                need.add(u);
                need.add(v);
            }
        }
        
        // Step 2: Try teaching each possible language (1..n)
        int ans = languages.length + 1; // Start with max possible (all users)
        for (int i = 1; i <= n; ++i) {
            int cans = 0; // Count of people who must learn language i

            // Check each user in the "need" set
            for (int v : need) {
                boolean found = false;
                // Does user v already know language i?
                for (int c : languages[v]) {
                    if (c == i) {
                        found = true;
                        break;
                    }
                }
                // If not, they must learn it
                if (!found) cans++;
            }

            // Take the minimum across all language choices
            ans = Math.min(ans, cans);
        }

        return ans;
    }
}
