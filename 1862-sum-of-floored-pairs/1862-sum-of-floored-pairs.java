// T.C: O(maxVal * log(maxVal))
// S.C: O(maxVal)
class Solution {
    public int sumOfFlooredPairs(int[] nums) {
        long MOD = 1_000_000_007;
        int maxi = 100_001;
        int n = nums.length;

        long[] freq = new long[2 * maxi + 1];
        long maxVal = 0;

        // freq and maximum number
        for (int num : nums) {
            freq[num]++;
            maxVal = Math.max(maxVal, num);
        }

        // prefix sum
        for (int i = 1; i <= 2 * maxi; i++) {
            freq[i] += freq[i - 1];   // freq[i] = number of elements <= i
        }

        long sum = 0;
        for (int num = 1; num <= maxVal; num++) {
            if (freq[num] == freq[num - 1]) continue;  // skip numbers not in nums

            long countNum = freq[num] - freq[num - 1];  // frequency of num
            long floorValue = 1;

            while (floorValue * num <= maxVal) {
                long left = floorValue * num;
                long right = Math.min((floorValue + 1) * num - 1, maxVal);

                long countInRange = freq[(int)right] - freq[(int)left - 1];
                sum = (sum + (countNum * floorValue % MOD) * countInRange % MOD) % MOD;

                floorValue++;
            }
        }

        return (int)sum;
    }
}