class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> {
                int diff = map.get(b) - map.get(a); // soldier count desc
                if (diff != 0) return diff;
                return b - a; // index desc if soldiers equal
            }
        );

        for (int i = 0; i < mat.length; i++) {
            int soldiers = 0;

            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) soldiers++;
            }

            map.put(i, soldiers);
            maxHeap.add(i);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[] ans = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            ans[i] = maxHeap.poll();
        }

        return ans;
    }
}