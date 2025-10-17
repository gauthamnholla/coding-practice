class Solution {

    public int maxPartitionsAfterOperations(String s, int k) {
        // Edge case: if k = 26, all letters are allowed → entire string is one partition
        if (k == 26)
            return 1;

        int n = s.length();
        char[] cs = s.toCharArray();

        // postCt[i] → number of partitions starting from index i (right side)
        // postMk[i] → bitmask of characters used in current right partition
        // postMkt[i] → bitmask of characters that appear multiple times (duplicates)
        int[] postCt = new int[n];
        int[] postMk = new int[n];
        int[] postMkt = new int[n];

        int pst = 1;     // count of partitions from right side
        int mark = 0;    // bitmask of characters in current segment
        int pmkt = 0;    // bitmask of duplicate characters in segment

        // -------------------------------
        // BACKWARD PASS (build post info)
        // -------------------------------
        // Move from right → left to precompute how partitions form
        for (int i = n - 1; i >= 0; i--) {
            int ci = cs[i] - 'a'; // convert character to 0–25 index

            // If character not seen yet in current partition
            if (!isMark(mark, ci))
                mark |= 1 << ci;
            else
                pmkt |= 1 << ci; // duplicate

            // If we have more than k unique chars → start a new partition
            if (Integer.bitCount(mark) == k + 1) {
                pst++;
                mark = 1 << ci;
                pmkt = 0;
            }

            // Store results for this suffix position
            postCt[i] = pst;
            postMk[i] = mark;
            postMkt[i] = pmkt;
        }

        int res = pst; // initial answer = number of suffix partitions
        int mkt = 0;   // duplicates for prefix
        mark = 0;      // bitmask for prefix chars
        int pc = 0;    // number of prefix partitions

        // -------------------------------
        // FORWARD PASS (main computation)
        // -------------------------------
        for (int i = 0; i < n; i++) {
            int ci = cs[i] - 'a';

            // Add this character to current partition or mark as duplicate
            if (!isMark(mark, ci))
                mark |= 1 << ci;
            else
                mkt |= 1 << ci;

            // If exceeded k unique chars → make a new partition
            if (Integer.bitCount(mark) == k + 1) {
                mark = 1 << ci;
                mkt = 0;
                pc++;
            }

            // Try to maximize partitions considering one character change operation
            if (Integer.bitCount(mark) == k) {
                // Case 1: Change a duplicate to a new unique char
                if (Integer.bitCount(mkt) > 0)
                    res = Math.max(res, pc + 1 + postCt[i]);

                // Case 2: Check merge possibilities with next segment
                if (isMark(mkt, ci) &&
                    isMark(postMkt[i], ci) &&
                    Integer.bitCount(postMk[i]) == k &&
                    Integer.bitCount(mark | postMk[i]) < 26)
                    res = Math.max(res, pc + 2 + postCt[i]);
            }
        }

        return res;
    }

    // Utility function to check if character idx is present in bitmask x
    public boolean isMark(int x, int idx) {
        return (x >> idx & 1) == 1;
    }
}
