import java.util.*;

public class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> result = new HashSet<>(); // Stores all unique results
        Set<Integer> prev = new HashSet<>();   // Stores results for subarrays ending at previous index

        for (int num : arr) {
            Set<Integer> curr = new HashSet<>();

            // For each OR result from previous, OR with current number
            for (int x : prev)
                curr.add(x | num);

            // Start a new subarray with the current number
            curr.add(num);

            // Add all results for this position to global set
            result.addAll(curr);

            // Move curr to prev for the next iteration
            prev = curr;
        }
        return result.size();
    }
}
