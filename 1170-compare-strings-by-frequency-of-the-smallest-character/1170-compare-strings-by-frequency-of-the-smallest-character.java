class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wFreq = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wFreq[i] = freq(words[i]);
        }

        Arrays.sort(wFreq);

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int qFreq = freq(queries[i]);
            int count = 0;
            for (int wf : wFreq) {
                if (wf > qFreq) count++;
            }
            res[i] = count;
        }
        return res;
    }

    private int freq(String s) {
        char min = 'z';
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c < min) {
                min = c;
                count = 1;
            } else if (c == min) {
                count++;
            }
        }
        return count;
    }
}
