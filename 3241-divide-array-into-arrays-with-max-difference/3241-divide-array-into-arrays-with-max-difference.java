class Solution {
    public int[][] divideArray(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) pq.offer(num);

        List<List<Integer>> resList = new ArrayList<>();

        while (pq.size() >= 3) {
            int low = pq.poll();
            int mid = pq.poll();
            int high = pq.poll();

            if (high - low <= k)
                resList.add(Arrays.asList(low, mid, high));
            else
                return new int[0][0]; // empty 2D array
        }

        // Convert to int[][]
        int[][] res = new int[resList.size()][3];
        for (int i = 0; i < resList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = resList.get(i).get(j);
            }
        }
        return res;
    }
}
