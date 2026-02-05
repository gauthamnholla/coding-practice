class Solution {
    public int distributeCandies(int n, int limit) {
        
        if (n > 3 * limit) return 0;

        int total = (n + 2) * (n + 1) / 2;

        int more1 = 0;
        if (n - (limit + 1) >= 0) {
            int t = n - (limit + 1);
            more1 = 3 * (t + 2) * (t + 1) / 2;
        }

        int more2 = 0;
        if (n - 2 * (limit + 1) >= 0) {
            int t = n - 2 * (limit + 1);
            more2 = 3 * (t + 2) * (t + 1) / 2;
        }

        return total - more1 + more2;
    }
}