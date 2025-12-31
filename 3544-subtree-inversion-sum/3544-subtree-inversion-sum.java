class Solution {
    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        Map<Integer, List<Integer>> map = graph(edges);
        return rec(map, nums, 0, -1, 0, 0, k, new Long[nums.length][2][k]);
    }
    
    
    private long rec(Map<Integer, List<Integer>> map, int[] nums, int node, int parent, int numInversions, int depth, int k, Long[][][] dp){
        
        if(dp[node][numInversions][depth] != null){
            return dp[node][numInversions][depth];
        }
        long flip = Integer.MIN_VALUE;
        if(depth == 0){
            flip = ((numInversions + 1) % 2 == 0 ? 1 : -1) * nums[node];
            for(int child : map.getOrDefault(node, new ArrayList<>())){
                if(child != parent){
                    flip += rec(map, nums, child, node, (numInversions + 1) % 2, k - 1, k, dp);
                }
            }
        }
        long skip = (numInversions % 2 == 0 ? 1 : -1) * nums[node];
        for(int child : map.getOrDefault(node, new ArrayList<>())){
            if(child != parent){
                skip += rec(map, nums, child, node, numInversions, Math.max(0, depth - 1), k, dp);
            }
        }
        return dp[node][numInversions][depth] = Math.max(flip, skip);
        
    }
    
    private Map<Integer, List<Integer>> graph(int[][] edges){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] e : edges){
            map.computeIfAbsent(e[0], k -> new ArrayList<Integer>()).add(e[1]);
            map.computeIfAbsent(e[1], k -> new ArrayList<Integer>()).add(e[0]);
        }
        return map;
    }
}