class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // Sort children by greed
        Arrays.sort(s); // Sort cookies by size

        int n = g.length, m = s.length;
        int i = 0, j = 0; // i = cookie index, j = child index

        // Try to satisfy each child with the smallest sufficient cookie
        while (i < m && j < n) {
            if (g[j] <= s[i]) j++; // Cookie fits this child → move to next child
            i++; // Move to next cookie
        }

        return j; // Number of satisfied children
    }
}
