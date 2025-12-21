import java.util.*;

class Solution {
    static final int MOD = 1000000007;
    static final int MX = 100001;
    int[] primeFactors = new int[MX];

    public Solution() {
        precomputePrimeFactors();
    }

    private void precomputePrimeFactors() {
        for (int i = 2; i < MX; i++) {
            if (primeFactors[i] == 0) {
                for (int j = i; j < MX; j += i) {
                    primeFactors[j]++;
                }
            }
        }
    }

    public int maximumScore(List<Integer> numsList, int k) {
        int n = numsList.size();

        // Convert List<Integer> → int[]
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = numsList.get(i);

        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && primeFactors[nums[stack.peek()]] < primeFactors[nums[i]]) {
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) left[i] = stack.peek();
            stack.push(i);
        }

        List<int[]> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            elements.add(new int[]{nums[i], left[i], right[i], i});
        }
        elements.sort((a, b) -> b[0] - a[0]);

        int ans = 1;
        for (int[] e : elements) {
            int v = e[0], l = e[1], r = e[2], i = e[3];
            long total = (long)(i - l) * (r - i);

            if (total >= k) {
                ans = (int)((long) ans * pow(v, k) % MOD);
                break;
            }
            ans = (int)((long) ans * pow(v, (int) total) % MOD);
            k -= total;
        }

        return ans;
    }

    private int pow(int base, int exp) {
        long result = 1, x = base;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * x) % MOD;
            x = (x * x) % MOD;
            exp >>= 1;
        }
        return (int) result;
    }
}
