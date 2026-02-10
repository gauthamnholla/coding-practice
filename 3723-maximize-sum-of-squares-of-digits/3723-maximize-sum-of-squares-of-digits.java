class Solution {
        public String maxSumOfSquares(int n, int s) {
        if (s > n * 9) return "";
        int q = s / 9, r = s % 9;
        StringBuilder sb = new StringBuilder().repeat('9', q);
        if (r > 0)
            sb.append(r);
        sb.repeat('0', n - sb.length());
        return sb.toString();
    }
}