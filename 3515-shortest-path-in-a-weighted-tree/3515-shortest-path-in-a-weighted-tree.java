import java.util.ArrayList;
import java.util.List;

public class Solution {

    int in[], out[], dist[], parent[], edgeWeight[], timer = 0;
    List<int[]>[] adj;

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        in = new int[n + 1];
        out = new int[n + 1];
        dist = new int[n + 1];
        parent = new int[n + 1];
        edgeWeight = new int[n + 1];
        dfs(1, 0, 0);
        Fenwick fenwick = new Fenwick(n);
        List<Integer> ansList = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                int u = query[1], v = query[2], newW = query[3], child = parent[v] == u ? v : u;
                int diff=newW-edgeWeight[child];
                edgeWeight[child] = newW;
                fenwick.updateRange(in[child], out[child], diff);
            } else {
                int x = query[1], delta = fenwick.query(in[x]);
                ansList.add(dist[x] + delta);
            }
        }
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(int node, int par, int dist) {
        parent[node] = par;
        this.dist[node] = dist;
        in[node] = ++timer;
        for (int[] neighbor : adj[node]) {
            int nei = neighbor[0], w = neighbor[1];
            if (nei == par) continue;
            edgeWeight[nei] = w;
            dfs(nei, node, dist + w);
        }
        out[node] = timer;
    }

    static class Fenwick {
        int n, fenwick[];
        public Fenwick(int n) {
            this.n = n;
            fenwick = new int[n + 1];
        }
        private void update(int i, int delta) {
            while (i <= n) {
                fenwick[i] += delta;
                i += i & -i;
            }
        }
        public void updateRange(int l, int r, int delta) {
            update(l, delta);
            update(r + 1, -delta);
        }
        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += fenwick[i];
                i -= i & -i;
            }
            return sum;
        }
    }

}