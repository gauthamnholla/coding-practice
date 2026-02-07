class Solution {
    public long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public long maxScore(int[] arr) {
        long arrayGCD = 0;
        long arrayLCM = 1;

        for (int num : arr) {
            arrayGCD = gcd(arrayGCD, num);
            arrayLCM = lcm(arrayLCM, num);
        }

        long maxFactor = arrayGCD * arrayLCM;

        for (int i = 0; i < arr.length; i++) {
            long subGCD = 0;
            long subLCM = 1;

            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    subGCD = gcd(subGCD, arr[j]);
                    subLCM = lcm(subLCM, arr[j]);
                }
            }

            maxFactor = Math.max(maxFactor, subGCD * subLCM);
        }

        return maxFactor;
    }
}