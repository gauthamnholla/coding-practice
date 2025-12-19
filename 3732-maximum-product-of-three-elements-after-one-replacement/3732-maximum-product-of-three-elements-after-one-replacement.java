class Solution {
    long maxProduct(int[] nums) {
    long a = 0, b = 0;
    for (int x : nums) {
        long ax = Math.abs(x);
        if (ax >= a) { b = a; a = ax; }
        else if (ax > b) { b = ax; }
    }
    return 100000L * a * b;
}
}