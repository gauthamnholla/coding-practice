class Solution {
    public int[] minCosts(int[] cost) {

        int[] res = new int[cost.length];
        
        for (int i = 0; i < res.length; i++) 
            res[i] = Math.min(i > 0 ? res[i - 1] : Integer.MAX_VALUE, cost[i]);
        return res;}
}