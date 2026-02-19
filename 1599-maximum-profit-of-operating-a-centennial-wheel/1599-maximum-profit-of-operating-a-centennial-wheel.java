class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int customersWaiting = 0, customersRiding = 0, i = 0, maxp = 0;
        List<Integer> profit = new ArrayList<>();
        while (customersWaiting > 0 || i < customers.length) {
            if (i < customers.length) {
                customersWaiting += customers[i];
            }
            if (customersWaiting > 3) {
                customersRiding += 4;
                customersWaiting -= 4;
            } else {
                customersRiding += customersWaiting;
                customersWaiting = 0;
            }
            i++;
            int x = customersRiding * boardingCost - i * runningCost;
            if (profit.isEmpty()) {
                maxp = x;
            } else {
                maxp = Math.max(maxp, x);
            }
            profit.add(x);
        }
        if (maxp <= 0) {
            return -1;
        }
        return profit.indexOf(maxp) + 1;
    }
}