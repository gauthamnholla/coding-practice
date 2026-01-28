class Solution {
    public boolean isPrefixString(String s, String[] words) {

        int wordIdx = 0;
        int charIdx = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);

            if (wordIdx == words.length) {
                return false;
            }

            char ch2 = words[wordIdx].charAt(charIdx++);

            if (ch1 != ch2) {
                return false;
            }

            if (charIdx == words[wordIdx].length()) {
                wordIdx++;
                charIdx = 0;
            }
        }

        return charIdx == 0;
    }
}