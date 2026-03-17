class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget,
                                  List<List<Integer>> composition,
                                  List<Integer> stock,
                                  List<Integer> cost) {
        int maxAlloys = 0;

        for (int i = 0; i < k; i++) {
            long lo = 0;
            // Upper bound: even if everything is free, can't exceed stock + budget
            long hi = (long) budget + 1_000_000_00L;

            while (lo <= hi) {
                long mid = lo + (hi - lo) / 2;

                if (canProduce(composition.get(i), stock, cost, budget, mid, n)) {
                    maxAlloys = (int) Math.max(maxAlloys, mid);
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return maxAlloys;
    }

    private boolean canProduce(List<Integer> comp, List<Integer> stock,
                                List<Integer> cost, long budget, long amount, int n) {
        long totalCost = 0;

        for (int j = 0; j < n; j++) {
            long needed   = (long) comp.get(j) * amount;
            long available = (long) stock.get(j);
            long toBuy    = Math.max(0, needed - available);

            totalCost += toBuy * (long) cost.get(j);

            if (totalCost > budget) return false;
        }

        return true;
    }
}