class Solution {
    public int[] lexSmallestNegatedPerm(int n, long target) {

        long max = 1L * n * (n + 1) / 2; // sum of 1..n

        // feasibility check:
        // target must be reachable using ±i flips
        if (Math.abs(target) > max ||
            ((max - target) & 1) == 1) { // parity mismatch => impossible
            return new int[]{};
        }

        long required = (max - target) / 2; // sum of numbers to flip negative

        int[] arr = new int[n];
        int id = 0;
        int cur = n;

        Set<Integer> neg = new HashSet<>();

        // greedy subset sum using largest numbers first
        while (required > 0) {
            while (cur > required) cur--; // find biggest usable number

            required -= cur;
            neg.add(cur);

            arr[id++] = -cur; // place negative early for lexicographically smallest
            cur--;
        }

        // fill remaining numbers (positive ones)
        List<Integer> rem = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!neg.contains(i)) rem.add(i);
        }

        int p = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) { // fill unused positions
                arr[i] = rem.get(p++);
            }
        }

        return arr;
    }
}