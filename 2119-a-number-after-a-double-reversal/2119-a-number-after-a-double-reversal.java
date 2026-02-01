class Solution {
    int reverse(int num) {
        int temp = num;
        int rem = 0;
        while (temp > 0) {
            rem = rem * 10 + temp % 10;
            temp /= 10;
        }
        return rem;
    }

    public boolean isSameAfterReversals(int num) {
        return reverse(reverse(num)) == num;
    }
}