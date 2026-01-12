class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int[] diffCity = new int[boxes.length+1];
        int[] weights = new int[boxes.length+1];
        
        for (int i = 0; i < boxes.length; i++) {
            diffCity[i+1] = diffCity[i] + ((i != 0 && boxes[i][0] == boxes[i-1][0]) ? 0 : 1);
            weights[i+1] = weights[i] + boxes[i][1];
        }
        int[] dp = new int[boxes.length+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // {index, (dp[x] - c[x+1])}
        pq.offer(new int[] {0, -1}); // necessary offset for dp[1]
        for (int i = 1; i <= boxes.length; i++) {
            while (!pq.isEmpty() && (i - pq.peek()[0] > maxBoxes || weights[i] - weights[pq.peek()[0]] > maxWeight)) {
                pq.poll();  // filter out those mins values no longer in range
            }
            
            dp[i] = pq.peek()[1] + diffCity[i] + 2; // reassemble (dp[i] = min(2 + dC + dp[j])
            if (i != boxes.length) {
                pq.offer(new int[] {i, dp[i] - diffCity[i+1]}); // add current val into min heap
            }
        }
        return dp[boxes.length];
    }
}