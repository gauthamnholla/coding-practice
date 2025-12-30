class Solution {
    private ArrayList<int[]>[] adj;
    private static final int[][] map = new int[50001][2]; //reuse this map for efficiency
    private int[] nums;
    private int minCount = 1, maxLength = 0;
    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        int n = edges.length + 1, max = 0;
        this.adj = new ArrayList[n];
        this.nums = nums;
        for(int i = 0; i < n; i++) { //clear all related map entries
            adj[i] = new ArrayList<>();
            map[nums[i]][0] = 0;
            map[nums[i]][1] = 0;
        }
        for(int[] edge : edges) { //build adj list
            int a = edge[0], b = edge[1], c = edge[2];
            adj[a].add(new int[] {b, c});
            adj[b].add(new int[] {a, c});
        }
        dfs(0, 0, new int[2], new int[2]);
        return new int[] {maxLength, minCount};
    }
    private void dfs(int index, int parent, int[] pathStart, int[] duplicate) {
        int num = nums[index];
        int[] current = map[nums[parent]], prev = map[num]; //map[num] = {prevNumSum, prevNumDepth}
        
        if(prev[1] < duplicate[1]) { //if the previous occurrence of the current number came before the previous duplicate number, the current path must start directly after the previous occurrence
            if(prev[0] > pathStart[0]) pathStart = prev;
        }else { //otherwise, the previous duplicate number came first and the path starts directly after it, and the path now contains 2 copies of the current number, making it our new duplicate
            if(duplicate[0] > pathStart[0]) pathStart = duplicate;
            duplicate = prev;
        }

        //track global best path
        //totalMaxLength = max(totalMaxLength, currentLength - pathStartLength)
        if(current[0] - pathStart[0] > maxLength) {
            maxLength = current[0] - pathStart[0];
            minCount = current[1] - pathStart[1] + 1;
        }else if(current[0] - pathStart[0] == maxLength && current[1] - pathStart[1] + 1 < minCount) minCount = current[1] - pathStart[1] + 1;

        //backtracking
        map[num] = new int[] {0, current[1] + 1};
        for(int[] next : adj[index]) {
            if(next[0] == parent) continue;
            map[num][0] = current[0] + next[1];
            dfs(next[0], index, pathStart, duplicate);
        }
        map[num] = prev;
    }
}