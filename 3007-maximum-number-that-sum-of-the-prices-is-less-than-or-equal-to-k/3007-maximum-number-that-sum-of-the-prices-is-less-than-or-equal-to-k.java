class Solution {
    private long getCurrSum(long num, int x) {
        long count = 0;
        for (int b = x - 1; b < 60; b += x) {
			// This gives the count of full cycles of bits in the binary representation of numbers from 1 to num.
            long fullCycles = num / (1L << (b + 1)); 
			//  count of set bits at the current position, given by (1L << b).
            count += fullCycles * (1L << b); 
			 // Calculate the remaining bits after the full cycles 
            long remaining = num % (1L << (b + 1));
			 // This part ensures that only the relevant remaining bits are considered, and any excess bits are ignored.
            count += Math.max(0, remaining - (1L << b));
        }
        return count;
    }
	
    public long findMaximumNumber(long k, int x) {
        long l = 1, r = (long) 1e15, result = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (getCurrSum(mid + 1, x) <= k) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return result;
    }
}