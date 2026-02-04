class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        if (purchaseAmount < 5){
            return 100;
        }

        else if (purchaseAmount >= 10 && purchaseAmount % 10 == 0) {
            return 100 - purchaseAmount;
        }

        int digit = purchaseAmount % 10;
        int val = (purchaseAmount / 10);
        if (digit >= 5 || val == 0) {
            val = val + 1;
        }
        int result = 100 - (val * 10);
        
        return result;
    }
}