class Solution {
    public long minimumTotalCost(int[] a, int[] b) {
        // repeat is the number of a[i] == b[i]
        int n = a.length;
        int repeat = 0;   
        // s is the sum of i that satisfies a[i] == b[i]
        long s = 0; 
        // val is the only candidate for majority  
        int val = -1;
        int x = 0;   
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[i]) {
                if (x == 0) val = a[i];
                if (val == a[i]) x--;
                else x++;
                repeat++;
                s += i;
            }
        }
        // cnt is the count of val
        int cnt = 0;  
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[i] && a[i] == val)
                cnt++;
        }
        // less than half, so s is the result
        if (cnt * 2 < repeat) return s;
        // the number which need to be swapped externally
        int m = 2 * cnt - repeat;
        for (int i = 0; i < n && m > 0; ++i) {
            // index satisfying this condition can be used for swap
            if (a[i] != val && b[i] != val && a[i] != b[i]) {
                s += i;
                m--;
            }
        }
        if (m > 0) return -1;
        return s;
    }
}