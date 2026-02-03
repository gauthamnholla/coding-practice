class Solution {
    public int countAnagrams(String s) {
        long MOD = 1_000_000_007;
        String[] words = s.split(" ");
        int maxLen = 0;
        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
        }
        
        long[] fact = new long[maxLen + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxLen; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        
        long ans = 1;
        for (String word : words) {
            long count = fact[word.length()];
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }
            for (int f : freq) {
                if (f > 1) count = count * modInverse(fact[f], MOD) % MOD;
            }
            ans = ans * count % MOD;
        }
        return (int) ans;
    }
    
    private long modInverse(long x, long mod) {
        long res = 1, pow = mod - 2;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * x % mod;
            x = x * x % mod;
            pow >>= 1;
        }
        return res;
    }
}