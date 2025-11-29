class Solution {
    public long countMajoritySubarrays(int[] A, int target) {
        int n = A.length, pre = n + 1;
        long[] count = new long[2 * n + 2];
        long[] acc = new long[2 * n + 2];
        long res = 0;
        count[pre] = acc[pre] = 1;
        for (int a : A) {
            pre += (a == target ? 1 : -1);
            acc[pre] = ++count[pre] + acc[pre - 1];
            res += acc[pre - 1];
        }
        return res;
    }
}
