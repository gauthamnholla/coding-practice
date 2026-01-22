class Solution {

    // Disjoint Set Union (Union-Find) data structure
    static class DSU {

        // parent[i] = representative (parent) of node i
        static int[] parent = new int[26];

        // size[i] = size of the set whose representative is i
        static int[] size = new int[26];

        // Constructor: initialize DSU
        // Each variable starts in its own set
        DSU() {
            for (int i = 0; i < 26; i++) {
                parent[i] = i;   // each node is its own parent
                size[i] = 1;     // initial size of each set is 1
            }
        }

        // Find with path compression
        // Returns the representative (root) of the set
        static int find(int ch) {
            // If current node is not its own parent,
            // recursively find the root and compress the path
            if (ch != parent[ch])
                parent[ch] = find(parent[ch]);

            return parent[ch];
        }

        // Union by size
        // Merges the sets of ch1 and ch2
        static void union(int ch1, int ch2) {

            // Find representatives of both nodes
            int pa = find(ch1);
            int pb = find(ch2);

            // If already in the same set, nothing to do
            if (pa == pb)
                return;

            // Attach smaller set under larger set
            if (size[pa] > size[pb]) {
                parent[pb] = pa;
                size[pa] += size[pb];
            } else {
                parent[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    public boolean equationsPossible(String[] equations) {

        // Initialize DSU for 26 lowercase letters
        DSU dsu = new DSU();

        // -------- First pass: process all "==" equations --------
        // Build connected components for equal variables
        for (String str : equations) {

            // Check if equation is of type "x==y"
            if (str.charAt(1) == '=') {

                // Convert characters to indices (0 to 25)
                int ch1 = str.charAt(0) - 'a';
                int ch2 = str.charAt(3) - 'a';

                // Union the sets if they are not already connected
                if (DSU.find(ch1) != DSU.find(ch2))
                    DSU.union(ch1, ch2);
            }
        }

        // -------- Second pass: process all "!=" equations --------
        // Check for contradictions
        for (String str : equations) {

            // Check if equation is of type "x!=y"
            if (str.charAt(1) == '!') {

                // Convert characters to indices
                int ch1 = str.charAt(0) - 'a';
                int ch2 = str.charAt(3) - 'a';

                // If both variables belong to the same set,
                // inequality cannot be satisfied
                if (DSU.find(ch1) == DSU.find(ch2))
                    return false;
            }
        }

        // If no contradiction found, equations are satisfiable
        return true;
    }
}