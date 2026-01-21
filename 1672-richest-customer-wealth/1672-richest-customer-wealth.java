class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;

        for (int[] customer : accounts) {
            int sum = 0;
            for (int money : customer) {
                sum += money;
            }
            max = Math.max(max, sum);
        }

        return max;
    }
}