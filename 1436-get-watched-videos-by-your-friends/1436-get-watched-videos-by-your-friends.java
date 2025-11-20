import java.util.*;

class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(id);
        visited[id] = true;
        int curLevel = 0;

        // BFS to reach the desired level
        while (!q.isEmpty() && curLevel < level) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int u = q.poll();
                for (int v : friends[u]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        q.add(v);
                    }
                }
            }
            curLevel++;
        }

        // Now q contains all nodes at distance == level (if any)
        Map<String, Integer> freq = new HashMap<>();
        while (!q.isEmpty()) {
            int person = q.poll();
            for (String video : watchedVideos.get(person)) {
                freq.put(video, freq.getOrDefault(video, 0) + 1);
            }
        }

        List<String> ans = new ArrayList<>(freq.keySet());
        ans.sort((a, b) -> {
            int ca = freq.get(a);
            int cb = freq.get(b);
            if (ca != cb) return Integer.compare(ca, cb);
            return a.compareTo(b);
        });

        return ans;
    }
}
