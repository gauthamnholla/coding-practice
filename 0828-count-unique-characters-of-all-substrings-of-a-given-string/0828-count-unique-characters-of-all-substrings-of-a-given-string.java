class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[] last = new int[26];
        int[] left = new int[n];
        int[] right = new int[n];

        // Initialize last seen index for left calculation
        Arrays.fill(last, -1);
        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'A';
            left[i] = last[idx];
            last[idx] = i;
        }

        // Reuse the last array for right calculation
        Arrays.fill(last, n);
        for (int i = n - 1; i >= 0; --i) {
            int idx = s.charAt(i) - 'A';
            right[i] = last[idx];
            last[idx] = i;
        }

        // Use the contribution technique
        int total = 0;
        for (int i = 0; i < n; ++i) {
            total += (i - left[i]) * (right[i] - i);
        }
        return total;
    }
}