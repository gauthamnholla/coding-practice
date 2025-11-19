import java.util.Arrays;

class Solution {
    
    // Disjoint Set Union (DSU) data structure
    class DSU {
        private int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // Finds the representative (root) of the set containing x
        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            // Path compression for optimization
            return parent[x] = find(parent[x]);
        }

        // Merges the sets containing x and y
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DSU dsu = new DSU(n);
        boolean[] result = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0];
            int v = requests[i][1];

            // Find the current friend groups of u and v
            int rootU = dsu.find(u);
            int rootV = dsu.find(v);
            
            // If they are not already friends, check for restrictions
            if (rootU != rootV) {
                boolean canBeFriends = true;
                // Check if this new friendship violates any restriction
                for (int[] restriction : restrictions) {
                    int restrictedX = restriction[0];
                    int restrictedY = restriction[1];
                    
                    int rootX = dsu.find(restrictedX);
                    int rootY = dsu.find(restrictedY);

                    // If u's group contains one restricted person and v's group contains the other,
                    // they cannot become friends.
                    if ((rootX == rootU && rootY == rootV) || (rootX == rootV && rootY == rootU)) {
                        canBeFriends = false;
                        break;
                    }
                }

                result[i] = canBeFriends;
                if (canBeFriends) {
                    // If the request is successful, unite their groups
                    dsu.union(u, v);
                }
            } else {
                // If they are already friends, the request is always successful
                result[i] = true;
            }
        }
        
        return result;
    }
}
