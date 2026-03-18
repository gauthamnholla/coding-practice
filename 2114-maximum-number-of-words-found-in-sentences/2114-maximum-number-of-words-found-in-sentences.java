class Solution {
    public int mostWordsFound(String[] sentences) {
        int max = 0;

        for (String sentence : sentences) {
            int spaces = 0;
            for (char c : sentence.toCharArray()) {
                if (c == ' ') spaces++;
            }
            max = Math.max(max, spaces + 1);
        }

        return max;
    }
}