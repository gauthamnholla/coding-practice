class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) 
    {
           if (desiredTotal <= 0) return true;
        int maxSum = (maxChoosableInteger * (maxChoosableInteger + 1)) / 2;
        if (maxSum < desiredTotal) return false;

        Boolean[] memo = new Boolean[1 << maxChoosableInteger];

        BiFunction<Integer, Integer, Boolean> canWin = new BiFunction<>() {
            @Override
            public Boolean apply(Integer desired, Integer used) {
                if (memo[used] != null) return memo[used];

                for (int i = 1; i <= maxChoosableInteger; i++) {
                    int bit = 1 << (i - 1);
                    if ((used & bit) != 0) continue;

                    if (i >= desired) {
                        memo[used] = true;
                        return true;
                    }

                    if (!this.apply(desired - i, used | bit)) {
                        memo[used] = true;
                        return true;
                    }
                }
                memo[used] = false;
                return false;
            }
        };

        return canWin.apply(desiredTotal, 0);
    } 
}