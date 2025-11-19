class Solution {
    public int magicalString(int n) {
        if (n == 0) return 0;
        if (n <= 3) {
            // prefix "122"
            return (n == 1) ? 1 : 1; // first 1 or first 2 chars contain exactly one '1'
        }

        int[] s = new int[n + 1]; // store as ints 1 or 2
        s[0] = 1; s[1] = 2; s[2] = 2;
        int head = 2;           // read pointer (reads counts)
        int tail = 3;           // next write position
        int num = 1;            // next number to write (1 or 2)
        int countOnes = 1;      // we already have s[0] = 1

        // s[1] and s[2] are 2 -> they don't add to countOnes
        while (tail < n) {
            int times = s[head];              // how many times to write `num`
            for (int i = 0; i < times && tail < n; i++) {
                s[tail++] = num;
                if (num == 1) countOnes++;
            }
            num = 3 - num; // flip 1<->2
            head++;
        }

        return countOnes;
    }
}
