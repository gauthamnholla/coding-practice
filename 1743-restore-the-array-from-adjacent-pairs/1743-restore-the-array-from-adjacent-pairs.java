class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] p : adjacentPairs) {
            graph.computeIfAbsent(p[0], x -> new ArrayList<>()).add(p[1]);
            graph.computeIfAbsent(p[1], x -> new ArrayList<>()).add(p[0]);
        }

        // find endpoint (degree = 1)
        int start = 0;
        for (int key : graph.keySet()) {
            if (graph.get(key).size() == 1) {
                start = key;
                break;
            }
        }

        int n = adjacentPairs.length;
        int[] res = new int[n + 1];
        boolean[] seen = new boolean[200001];

        res[0] = start;
        seen[start + 100000] = true;

        for (int i = 1; i <= n; i++) {
            for (int nei : graph.get(res[i - 1])) {
                if (!seen[nei + 100000]) {
                    res[i] = nei;
                    seen[nei + 100000] = true;
                    break;
                }
            }
        }
        return res;
    }
}