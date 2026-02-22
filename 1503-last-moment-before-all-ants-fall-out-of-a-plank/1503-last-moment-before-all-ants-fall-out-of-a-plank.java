class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxl = 0;
        for(int i : left) maxl = Math.max(maxl, i);
        int maxr = 0;
        for(int i : right) maxr = Math.max(maxr, n - i);
        return Math.max(maxl, maxr);
    }
}