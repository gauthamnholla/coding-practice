import java.util.*;
class Solution {
    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        int parent[] = new int[n];
        //Downscore is the maximum score while moving downward in the tree by consider root as node 0
        int downScore[] = new int[n];
        int result[] = new int[n];
        List<Integer> adj[] = new List[n];

        for (int i = 0; i < n; i++) {
            parent[i] = -1;
            downScore[i] = 0;

            if (good[i] == 0) {
                good[i] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }

        dfs(adj, good, 0, - 1, parent, downScore);
       // System.out.println(Arrays.toString(downScore));

        populateMaxScore(adj, good, 0, - 1, parent, downScore, result);

        // for (int i = 0; i < n; i++) {
        //     result[i] = downScore[i];

        //     if (parent[i] != -1) {
        //         int temp = downScore[parent[i]] - Math.max(downScore[i], 0);
        //         result[i] = result[i] + Math.max(temp, 0);
                
        //     }
        // }

        return result;

        
        
    }

    public void dfs(List<Integer> adj[], int[] good, int start, int prev, int parent[], int downScore[]) {
        parent[start] = prev;
        downScore[start] = good[start];

        for (int v : adj[start]) {
            if (v == prev) {
                continue;
            }
            dfs(adj, good, v, start, parent, downScore);

            downScore[start] = downScore[start] + Math.max(downScore[v], 0); 
        }
    }

     public void populateMaxScore(List<Integer> adj[], int[] good, int start, int prev, int parent[], int downScore[], int result[]) {
        result[start] = downScore[start];

         if (parent[start] != -1) {
             int temp = result[parent[start]] - Math.max(downScore[start], 0);
             result[start] = Math.max(temp, 0) + result[start];
         }

        for (int v : adj[start]) {
            if (v == prev) {
                continue;
            }
            populateMaxScore(adj, good, v, start, parent, downScore, result);
        }
    }
}