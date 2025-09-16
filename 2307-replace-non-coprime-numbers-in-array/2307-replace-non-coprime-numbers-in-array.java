class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        // We will use a stack-like list to store the "merged" sequence.
        List<Integer> stack = new ArrayList<>();
        
        // Process each number in the input array
        for (int num : nums) {
            // Keep merging as long as the current number shares a GCD > 1 with the last stack element
            while (!stack.isEmpty()) {
                int top = stack.get(stack.size() - 1);  // look at last element in stack
                int g = gcd(top, num);                  // compute gcd of top and current
                
                if (g == 1) {  // If coprime, stop merging
                    break;
                }
                
                // If not coprime → merge top and num into their LCM
                stack.remove(stack.size() - 1);         // pop the top
                num = (top / g) * num;                  // update num as LCM(top, num)
            }
            // Push the (possibly merged) number back into stack
            stack.add(num);
        }
        
        // Final stack represents the sequence after all replacements
        return stack;
    }
    
    // Helper function: Greatest Common Divisor (Euclidean algorithm)
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
