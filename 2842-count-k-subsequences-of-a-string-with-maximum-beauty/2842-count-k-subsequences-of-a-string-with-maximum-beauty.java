class Solution {
        public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int n = s.length(), count[] = new int[26];
        for (int i = 0; i < n; ++i)
            count[s.charAt(i) - 'a']++;
        Arrays.sort(count);
        if (k > 26 || count[26 - k] == 0)
            return 0;
        long res = 1, comb = 1, mod = (long)1e9 + 7, bar = count[26 - k], pend = 0;
        for (int freq : count) {
            if (freq > bar) {
                k--;
                res = res * freq % mod;
            }
            if (freq == bar) pend++;
        }
        for (int i = 0; i < k; ++i) {
            comb = comb * (pend - i) / (i + 1);
            res = res * bar % mod;
        }
        return (int)(res * comb % mod);
    }
}