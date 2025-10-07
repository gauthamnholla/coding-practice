// Main Solution class
class Solution {

    public int[] avoidFlood(int[] rain) {
        int n = rain.length;

        // Union-Find data structure to efficiently find the next available "dry day"
        UnionFind uf = new UnionFind(n + 1);

        // Map to track which day each lake was last filled
        Map<Integer, Integer> map = new HashMap<>();

        // Result array
        // -1 → raining day (cannot dry any lake)
        // any positive number → lake that we choose to dry
        // default 1 for all dry days
        int[] res = new int[n];
        Arrays.fill(res, 1);

        // Loop through each day
        for (int i = 0; i < n; i++) {
            int lake = rain[i];

            // Case 1: Dry day (no rain)
            if (lake == 0) continue;

            // Case 2: Raining day
            res[i] = -1; // mark this as a rain day
            uf.unite(i); // mark this day as "used" (no longer available for drying later)

            // If the same lake was filled before
            if (map.containsKey(lake)) {
                int prev = map.get(lake);  // last day it was filled

                // Find the next available dry day after the previous rain
                int dry = uf.find(prev + 1);

                // If there is no dry day before this rain → flood occurs
                if (dry >= i) 
                    return new int[0];

                // Use that dry day to dry this lake
                res[dry] = lake;

                // Mark that day as used
                uf.unite(dry);

                // Update last filled day for this lake
                map.put(lake, i);
            } 
            // If this lake is being filled for the first time
            else {
                map.put(lake, i);
            }
        }

        // Return the final plan
        return res;
    }
}


// Union-Find (Disjoint Set) helper class
class UnionFind {
    int[] parent;

    // Constructor: initialize parent links for all days
    public UnionFind(int size) {
        parent = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            parent[i] = i;
        }
    }

    // Find with path compression
    // Returns the next available "dry day" >= i
    public int find(int i) {
        if (parent[i] == i)
            return i;
        parent[i] = find(parent[i]);
        return parent[i];
    }

    // Mark a day as "used" by linking it to the next day
    public void unite(int i) {
        parent[i] = find(i + 1);
    }
}
