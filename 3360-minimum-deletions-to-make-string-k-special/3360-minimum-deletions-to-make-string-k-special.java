class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26]; // Store frequency of each letter

        // Count frequency of each character
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq); // Sort frequencies
        int n = freq.length;
        int ans = word.length(); // Start with max possible deletions
        int l = 0, r = 0, curSum = 0;

        // Skip zero frequencies
        while (l < n && freq[l] == 0) l++;
        r = l;

        // Use two pointers to find valid range
        while (r < n) {
            // If freq difference > k, adjust left pointer
            while (freq[r] - freq[l] > k) {
                int range = freq[l] + k;
                int partFromOther = (n - r) * range;
                int remaining = word.length() - (curSum + partFromOther);
                ans = Math.min(ans, remaining);
                curSum -= freq[l];
                l++;
            }
            curSum += freq[r];
            r++;
        }

        // Final check for minimum deletions
        int remaining = word.length() - curSum;
        ans = Math.min(ans, remaining);
        return ans;
    }
}
