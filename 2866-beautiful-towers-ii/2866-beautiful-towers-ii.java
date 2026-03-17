class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] left = new long[n];   // best sum from 0..i with peak at i
        long[] right = new long[n];  // best sum from i..n-1 with peak at i
        Deque<Integer> stack = new ArrayDeque<>();

        // --- Build LEFT array using monotonic stack ---
        // For each i, find how far left maxHeights[i] can "extend"
        for (int i = 0; i < n; i++) {
            // Pop indices whose height is greater than current
            // because current caps them from the left side
            while (!stack.isEmpty() && maxHeights.get(stack.peek()) >= maxHeights.get(i)) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                // maxHeights[i] is the minimum so far → fills entire left side
                left[i] = (long)(i + 1) * maxHeights.get(i);
            } else {
                // j is the last index with height < maxHeights[i]
                int j = stack.peek();
                // reuse left[j] and extend from j+1 to i with maxHeights[i]
                left[i] = left[j] + (long)(i - j) * maxHeights.get(i);
            }
            stack.push(i);
        }

        stack.clear();

        // --- Build RIGHT array using monotonic stack ---
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && maxHeights.get(stack.peek()) >= maxHeights.get(i)) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                // maxHeights[i] is the minimum so far → fills entire right side
                right[i] = (long)(n - i) * maxHeights.get(i);
            } else {
                // j is the last index (to the right) with height < maxHeights[i]
                int j = stack.peek();
                // reuse right[j] and extend from i to j-1 with maxHeights[i]
                right[i] = right[j] + (long)(j - i) * maxHeights.get(i);
            }
            stack.push(i);
        }

        // --- Try every peak, subtract maxHeights[i] once (counted in both) ---
        long maxSum = 0;
        for (int i = 0; i < n; i++) {
            long total = left[i] + right[i] - maxHeights.get(i);
            maxSum = Math.max(maxSum, total);
        }

        return maxSum;
    }
}