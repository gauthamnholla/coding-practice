class Solution {
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;

        // We need separate temporary storage for numbers at even and odd positions.
        // Why Integer[] and not int[]? Because Collections.reverseOrder() works with Objects (like Integer), not primitives (like int).
        
        // 'even' will store numbers from even indices (0, 2, 4, ...).
        // The size is (n + 1) / 2. For n=5, it's (5+1)/2 = 3. For n=6, it's (6+1)/2 = 3. This correctly handles both odd and even 'n'.
        Integer[] even = new Integer[(n + 1) / 2];
        
        // 'odd' will store numbers from odd indices (1, 3, 5, ...).
        // The size is n / 2. For n=5, it's 5/2 = 2. For n=6, it's 6/2 = 3. This also correctly handles both cases.
        Integer[] odd = new Integer[n / 2];
    
        // Now, let's go through the input array 'nums' and separate the numbers based on their index.
        // 'i' is the index of the element in the original 'nums' array.
        for (int i = 0; i < n; i++) {
            // If the index 'i' is even (0, 2, 4, ...), the number belongs in the 'even' group.
            if (i % 2 == 0) {
                // We place it in the 'even' array. The index for the 'even' array is 'i / 2'.
                // Example: For index 0, it goes to even[0/2] = even[0]. For index 2, it goes to even[2/2] = even[1].
                even[i / 2] = nums[i];
            } else { // If the index 'i' is odd (1, 3, 5, ...), the number belongs in the 'odd' group.
                // We place it in the 'odd' array. The index for the 'odd' array is also 'i / 2'.
                // Example: For index 1, it goes to odd[1/2] = odd[0]. For index 3, it goes to odd[3/2] = odd[1].
                // This way, we correctly map the original indices to their place in the separated arrays.
                odd[i / 2] = nums[i];
            }
        }
    
        // Time to sort!
        // First, sort the 'even' array in ascending order (smallest to largest).
        Arrays.sort(even); 
        
        // Second, sort the 'odd' array in descending order (largest to smallest).
        // `Collections.reverseOrder()` is a comparator that makes `Arrays.sort` sort in reverse.
        Arrays.sort(odd, Collections.reverseOrder()); 
    
        // Now that we have our sorted 'even' and 'odd' numbers, we need to put them back together
        // into a single result array of the original size 'n'.
        int[] result = new int[n];
        
        // Let's iterate through the final 'result' array positions.
        for (int i = 0; i < n; i++) {
            // If the final index 'i' is even...
            if (i % 2 == 0) {
                // ...we take the next number from our sorted 'even' array.
                // The index in the 'even' array is again 'i / 2'.
                result[i] = even[i / 2];
            } else { // If the final index 'i' is odd...
                // ...we take the next number from our sorted 'odd' array.
                // The index in the 'odd' array is also 'i / 2'.
                result[i] = odd[i / 2];
            }
        }
    
        // Finally, we return the 'result' array, which now has the elements sorted as required.
        return result;
    }
}