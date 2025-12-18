class Solution {
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        String[] dp = new String[]{"", "", ""};
        int[] len = new int[3];
        for (int d : digits) {
            if (0 == d % 3) {
                for (int i = 0; i < 3; ++i) dp[i] = d + dp[i];
                continue;
            }
            String[] dp2 = new String[]{dp[0], dp[1], dp[2]};
            int[] nextLen = new int[]{len[0], len[1], len[2]};
            for (int i = 0; i < 3; ++i) {
                int j = ((0 == len[i] ? 0 : i) + d) % 3;
                String cand = d + dp[i];
                if (nextLen[j] <= len[i] + 1){
                    nextLen[j] = len[i] + 1;
                    dp2[j] = cand;
                }
            }
            len = nextLen;
            dp = dp2;            
        }
        if (dp[0].isEmpty()) return "";
        int i = 0;
        for (char c : dp[0].toCharArray()) if ('0' == c) i++; else break;
        return i == dp[0].length() ? "0" : dp[0].substring(i);
    }
}