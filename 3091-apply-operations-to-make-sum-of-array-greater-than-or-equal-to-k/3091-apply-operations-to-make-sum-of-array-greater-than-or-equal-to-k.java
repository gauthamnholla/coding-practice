class Solution {
        public int minOperations(int k) {
        int a = (int) Math.sqrt(k);
        return a + (k - 1) / a - 1;
    }
}