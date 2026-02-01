class Solution {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {

        Set<Integer>[] adjLists = new HashSet[n];
        for (int i = 0; i < n; i++) {
            adjLists[i] = new HashSet<>();
        }

        // Build adjacency list based on divisibility rule
        for (int i = threshold + 1; i <= n / 2; i++) {
            for (int j = 2 * i; j <= n; j += i) {
                adjLists[i - 1].add(j - 1);
                adjLists[j - 1].add(i - 1);
            }
        }

        int[] groups = new int[n];
        int groupId = 0;

        // Mark connected components using DFS
        for (int i = 0; i < n; i++) {
            if (groups[i] == 0) {
                groupId++;
                markGroups(i, adjLists, groups, groupId);
            }
        }

        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            result.add(groups[query[0] - 1] == groups[query[1] - 1]);
        }

        return result;
    }

    // DFS to mark all connected nodes with the same group ID
    private void markGroups(int node, Set<Integer>[] adjLists, int[] groups, int groupId) {
        groups[node] = groupId;
        for (Integer ngbr : adjLists[node]) {
            if (groups[ngbr] == 0) {
                markGroups(ngbr, adjLists, groups, groupId);
            }
        }
    }
}
