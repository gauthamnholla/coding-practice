class Solution {
    public int kthSmallest(int[][] mat, int k) {
    int C = Math.min(mat[0].length, k);

    var maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    maxHeap.add(0);
    for (var row : mat) {
        // max heap for the i-th row
        var nextHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int prevSum : maxHeap) {
            for (int c = 0; c < C; c++) {
                nextHeap.add(prevSum + row[c]);
                // keep next max heap size <= k
                if (nextHeap.size() > k) {
                    nextHeap.poll();
                }
            }
        }
        maxHeap = nextHeap;
    }
    return maxHeap.peek();
}
}