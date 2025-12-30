class Solution {
    private List<List<Integer>> graph;
    private int[] subtreeSize;

    private int dfs(int curr, int parent) {
        int size = 1;
        for (int nbr : graph.get(curr)) {
            if (nbr != parent) {
                size += dfs(nbr, curr);
            }
        }
        subtreeSize[curr] = size;
        return size;
    }

    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        graph = new ArrayList<>(n);
        subtreeSize = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(0, -1);

        int result = 0;
        for (int i = 0; i < n; i++) {
            boolean isGoodNode = true;
            int previousSize = -1;
            for (int nbr : graph.get(i)) {
                if (subtreeSize[nbr] < subtreeSize[i]) {
                    if (previousSize == -1) {
                        previousSize = subtreeSize[nbr];
                    } else if (previousSize != subtreeSize[nbr]) {
                        isGoodNode = false;
                        break;
                    }
                }
            }
            if (isGoodNode) {
                result++;
            }
        }

        return result;
    }
}