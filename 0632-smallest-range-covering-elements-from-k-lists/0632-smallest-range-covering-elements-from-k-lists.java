class Solution {
    // Compare two ranges: [a, b] (new) vs [c, d] (current best).
    // Return true if [a,b] is a better (smaller / earlier) range.
    private boolean chk(int a, int b, int c, int d) {
        if (b - a < d - c) return true;        // smaller length preferred
        else if (b - a == d - c) {
            if (a < c) return true;            // if equal length, earlier start preferred
        }
        return false;
    }

    public int[] smallestRange(List<List<Integer>> a) {
        int ansx = -100000, ansy = 100000;     // best range found so far
        int total_list = a.size();

        // Flatten all values with their source-list index: (value, listIndex)
        List<int[]> vp = new ArrayList<>();
        for (int i = 0; i < total_list; i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                vp.add(new int[]{a.get(i).get(j), i});
            }
        }

        // Sort pairs by value
        vp.sort(Comparator.comparingInt(x -> x[0]));

        Map<Integer, Integer> um = new HashMap<>(); // counts of list indices in current window
        int l = 0, r = 0;
        int n = vp.size();

        // Sliding window over sorted pairs
        while (r < n) {
            int num = vp.get(r)[0];
            int list = vp.get(r)[1];

            // Add right element's list to map
            um.put(list, um.getOrDefault(list, 0) + 1);

            // When window covers at least one element from every list, try to shrink from left
            while (um.size() >= total_list) {
                // Update best range if current [vp[l], vp[r]] is better
                if (chk(vp.get(l)[0], vp.get(r)[0], ansx, ansy)) {
                    ansx = vp.get(l)[0];
                    ansy = vp.get(r)[0];
                }

                // Remove left element and advance left pointer
                int old_list = vp.get(l)[1];
                um.put(old_list, um.get(old_list) - 1);
                if (um.get(old_list) == 0) {
                    um.remove(old_list);
                }
                l++;
            }

            r++; // expand right
        }

        return new int[]{ansx, ansy}; // return smallest range found
    }
}
