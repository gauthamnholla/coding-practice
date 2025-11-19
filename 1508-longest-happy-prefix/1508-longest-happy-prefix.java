class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int[] lps = new int[n];
        // build lps array
        int len = 0; // length of previous longest prefix-suffix
        for (int i = 1; i < n; i++) {
            while (len > 0 && s.charAt(i) != s.charAt(len)) {
                len = lps[len - 1];
            }
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
            } else {
                lps[i] = 0;
            }
        }
        int k = lps[n - 1];
        return s.substring(0, k);
    }
}
