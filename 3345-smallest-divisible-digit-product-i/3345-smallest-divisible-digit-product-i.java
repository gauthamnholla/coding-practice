class Solution {
    public int smallestNumber(int n, int t) {
        int num = n;

        while (getDigitProduct(num) % t != 0) {
            ++num;
        }

        return num;
    }

    private static final int getDigitProduct(int num) {
        int product = 1;

        while (num > 0) {
            product *= num % 10;
            num /= 10;
        }
        
        return product;
    }
}