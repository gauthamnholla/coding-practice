class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for(int i=0;i<n;i++)
        {
            if(manager[i]!=-1)
            {
                adj.get(manager[i]).add(i);
            }
        }
        return dfs(headID, adj,informTime);

    }
    public int dfs(int node,List<List<Integer>> adj , int[] informTime)
    {
        int res=0;
        for(int child : adj.get(node))
        {
            res=Math.max(res, dfs(child, adj, informTime));
        }
        return informTime[node]+res;
    }
}