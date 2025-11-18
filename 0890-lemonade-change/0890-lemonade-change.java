class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five_dollars = 0, ten_dollars = 0;

        for (int x : bills) {

            // If customer pays $5 → no change needed
            if (x == 5) {
                five_dollars++;
            } 
            // If customer pays $10 → need one $5 as change
            else if (x == 10) {
                if (five_dollars > 0) {
                    five_dollars--;
                    ten_dollars++;
                } else {
                    return false; // Cannot give $5 change
                }
            } 
            // If customer pays $20 → need $15 change
            else {
                // Best way: give one $10 + one $5
                if (five_dollars > 0 && ten_dollars > 0) {
                    five_dollars--;
                    ten_dollars--;
                }
                // Otherwise: give three $5 bills
                else if (five_dollars >= 3) {
                    five_dollars -= 3;
                }
                // Otherwise: impossible to give change
                else {
                    return false;
                }
            }
        }

        return true; // All customers got correct change
    }
}
