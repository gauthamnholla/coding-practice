class Solution {
    public int lastRemaining(int n) {
        boolean left = true;
        int head = 1; // -------------- initital starting no -------------
        int diff = 1; // -------------- initital diff between -------------
        int remaining = n; // -------------- initital condition -------------

        while(remaining > 1) {
            if(left || (remaining) % 2 == 1) {
                head = head + diff; //---------------- new head = head + diff ------------------
            }
            diff = diff*2; //----------------- no at next turn contins diff of 2 -----------
            remaining = remaining / 2; //----------------- capacity will reduce by 2 times --------
            left = !left; //-------------------- changing left ---------------- 
        }
        return head;
    }
}