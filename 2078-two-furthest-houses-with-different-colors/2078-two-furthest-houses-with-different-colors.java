class Solution {
    public int maxDistance(int[] cs) {
    int p_col2 = Integer.MAX_VALUE, res = 1;
    for (int i = 1; i < cs.length; ++i) {
        if (cs[i] != cs[0]) {
            p_col2 = Math.min(i, p_col2);
            res = i;
        }
        else
            res = Math.max(res, i - p_col2);
    }
    return res;        
}
}