class Solution {
    public int minOperations(int[] nums) {
        Stack<Integer> stack = new Stack<>(); // Track increasing elements
        int res = 0; // Count operations

        for (int n : nums) {
            // Remove larger elements to keep stack increasing
            while (!stack.isEmpty() && stack.peek() > n)
                stack.pop();

            if (n == 0) continue; // Skip zeros

            // If current number is greater than top, it's a new operation
            if (stack.isEmpty() || stack.peek() < n) {
                res++;
                stack.push(n);
            }
        }

        return res; // Return total operations
    }
}
