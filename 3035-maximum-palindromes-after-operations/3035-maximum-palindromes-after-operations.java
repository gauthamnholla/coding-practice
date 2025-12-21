class Solution {
        public int maxPalindromesAfterOperations(String[] words) {
        int n = words.length, A[] = new int[n];
        for (int i = 0; i < n; ++i)
            A[i] = words[i].length();
        Arrays.sort(A);
        Map<Character, Integer> count = new HashMap<>();
        for (String w : words)
            for (char c : w.toCharArray())
                count.put(c, count.getOrDefault(c, 0) + 1);

        int pairs = 0;
        for (Map.Entry<Character, Integer> entry : count.entrySet())
            pairs += entry.getValue() / 2;
        for (int i = 0; i < n; i++)
            if ((pairs -= A[i] / 2) < 0)
                return i;
        return n;
    }
}