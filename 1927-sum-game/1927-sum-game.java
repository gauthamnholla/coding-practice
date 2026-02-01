class Solution {
        public boolean sumGame(String s) {
        int n = s.length();
        double res = 0;
        for (int i = 0; i < n; ++i)
            res += (i < n / 2 ? 1 : -1) * (s.charAt(i) == '?' ? 4.5 : s.charAt(i) - '0');
        return res != 0;
    }
}