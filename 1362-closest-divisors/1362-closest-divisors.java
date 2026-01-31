class Solution {
    public int[] closestDivisors(int num) {
        int[] result1 = getPair(num + 1);
        int[] result2 = getPair(num + 2);
        
        // Return the pair with smaller absolute difference
        if (Math.abs(result1[1] - result1[0]) <= Math.abs(result2[1] - result2[0])) {
            return result1;
        } else {
            return result2;
        }
    }

    // Helper to find closest divisor pair
    private int[] getPair(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = sqrt; i >= 1; i--) {
            if (num % i == 0) {
                return new int[]{i, num / i};
            }
        }
        return new int[]{1, num}; // fallback
    }
}