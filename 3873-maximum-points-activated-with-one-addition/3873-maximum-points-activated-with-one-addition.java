class Solution {
    int[] parent;
    int[] size;

    public int find(int i) {
        if (parent[i] == i) return i;
        // Path Compression
        return parent[i] = find(parent[i]);
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            // Union by Size
            if (size[rootI] < size[rootJ]) {
                parent[rootI] = rootJ;
                size[rootJ] += size[rootI];
            } else {
                parent[rootJ] = rootI;
                size[rootI] += size[rootJ];
            }
        }
    }

    public int maxActivated(int[][] points) {
        int n = points.length;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        HashMap<Integer, Integer> xmap = new HashMap<>();
        HashMap<Integer, Integer> ymap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (xmap.containsKey(x)) union(i, xmap.get(x));
            else xmap.put(x, i);

            if (ymap.containsKey(y)) union(i, ymap.get(y));
            else ymap.put(y, i);
        }

        int max1 = 0, max2 = 0;
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                int currentSize = size[i];
                if (currentSize > max1) {
                    max2 = max1;
                    max1 = currentSize;
                } else if (currentSize > max2) {
                    max2 = currentSize;
                }
            }
        }

        return max1 + max2 + 1;
    }
}