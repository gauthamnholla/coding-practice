class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int[] res = new int[heights.length];
        int i = 0;
        while (i < heights.length) i = rec(heights, res, i);
        return res;
    }

    int rec(int[] heights, int[] res, int ind) {
        int startingHeight = heights[ind];
        int i = ind + 1;
        while (i < heights.length) {
            res[ind]++;
            if (heights[i] > heights[ind]) return i;
            i = rec(heights, res, i);
        }
        return heights.length;
    }
}