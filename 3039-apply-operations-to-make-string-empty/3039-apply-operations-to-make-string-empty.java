class Solution {
        public String lastNonEmptyString(String s) {
        int[] cnt = new int[26];
        int mx = 0;
        for (int i = 0; i < s.length(); ++i) {
            mx = Math.max(mx, ++cnt[s.charAt(i) - 'a']);
        }
        StringBuilder last = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if (mx == cnt[c - 'a']--) {
                last.append(c);
            }
        }
        return last.reverse().toString();
    }
}