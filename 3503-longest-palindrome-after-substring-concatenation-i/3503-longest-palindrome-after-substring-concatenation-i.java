class Solution {
    private int palinSize(String word) {
        int w = word.length(), res = 1;

        for (int wordLen = 0; wordLen < 2 * w - 1; wordLen++) {
            int idxLeft = wordLen / 2;
            int idxRght = idxLeft + wordLen % 2;

            if (word.charAt(idxLeft) == word.charAt(idxRght)) {
                while (idxLeft > 0 && idxRght < w - 1) {
                    if (word.charAt(idxLeft - 1) != word.charAt(idxRght + 1)) break;
                    idxLeft--;
                    idxRght++;}
                res = Math.max(res, idxRght - idxLeft + 1);} }
        return res;}

    public int longestPalindrome(String s, String t) {
        int m = s.length(), n = t.length(), ans = 1;

        for (int sRght = 0; sRght <= m; sRght++) {
            for (int tLeft = 0; tLeft <= n; tLeft++) {
                if (sRght + n - tLeft <= ans) continue;
                String word = s.substring(0, sRght) + t.substring(tLeft);
                ans = Math.max(ans, palinSize(word)); } }

        if (m > ans) ans = Math.max(ans, palinSize(s));
        if (n > ans) ans = Math.max(ans, palinSize(t));

        return ans;}
}