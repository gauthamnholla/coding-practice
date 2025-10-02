class Solution {
    public int maxBottlesDrunk(int numBottles, int x) {
        int ans = numBottles; // total bottles you start with (all drinkable initially)
        while (numBottles >= x) {
            numBottles -= x - 1; // trade x empty bottles to get 1 new full bottle (consume it)
            x++;                 // now need 1 more empty bottle for the next trade
            ans++;               // drank one more
        }
        return ans;
    }
}
