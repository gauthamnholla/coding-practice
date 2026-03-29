class Solution {
        public int minimumPrefixLength(int[] A) {
        int i = A.length - 1;
        while (i > 0 && A[i - 1] < A[i]) {
            i--;
        }
        return i;
    }
}