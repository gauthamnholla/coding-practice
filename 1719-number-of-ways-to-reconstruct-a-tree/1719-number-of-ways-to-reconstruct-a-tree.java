class Solution {
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] pair : pairs) {
            graph.putIfAbsent(pair[0], new HashSet<>());
            graph.putIfAbsent(pair[1], new HashSet<>());
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        int root = -1;
        int n = graph.size();
        for (int node : graph.keySet()) {
            if (graph.get(node).size() == n - 1) {
                root = node;
                break;
            }
        }

        if (root == -1) return 0;

        int result = 1;

        for (int node : graph.keySet()) {
            if (node == root) continue;

            int parent = -1;
            int parentDegree = Integer.MAX_VALUE;

            for (int neighbor : graph.get(node)) {
                if (graph.get(neighbor).size() >= graph.get(node).size() && graph.get(neighbor).size() < parentDegree) {
                    parent = neighbor;
                    parentDegree = graph.get(neighbor).size();
                }
            }

            if (parent == -1) return 0;

            for (int neighbor : graph.get(node)) {
                if (neighbor == parent) continue;
                if (!graph.get(parent).contains(neighbor)) return 0;
            }

            if (graph.get(parent).size() == graph.get(node).size()) {
                result = 2;
            }
        }

        return result;
    }
}