class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        // Initially, we can drink all given bottles
        int ans = numBottles;  
        int empty = numBottles; // Track empty bottles after drinking

        // While we have enough empty bottles to exchange for a new full one
        while (empty >= numExchange) {
            // Number of new full bottles we get in this round
            int newBottles = empty / numExchange;

            // Drink those new bottles → add to total count
            ans += newBottles;

            // Update empty bottles:
            //   - new empty bottles from drinking newBottles
            //   - plus leftover bottles from division
            empty = newBottles + (empty % numExchange);
        }

        // When not enough empties left for exchange, return total drunk bottles
        return ans;
    }
}
