class Solution {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }
        while (k > 0) {
            int temp = pq.poll();
            temp++;
            pq.add(temp);
            k--;
        }
        long result = 1;
        long MOD = 1_000_000_007;
        while (!pq.isEmpty()) {
            result = (result * pq.poll()) % MOD;
        }
        return (int) result;
    }
}