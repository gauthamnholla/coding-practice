import java.util.*;

public class Solution {
    public boolean reorderedPowerOf2(int n) {
        // Get digit count in string form
        String original = countDigits(n);
        // Try all powers of 2 for up to 1e9
        for (int i = 0; i < 31; i++) { // 2^0=1 up to 2^30 < 1e9
            int pow = 1 << i;
            if (original.equals(countDigits(pow))) {
                return true;
            }
        }
        return false;
    }
    
    // Helper: Return "sorted digits" string for a number
    private String countDigits(int n) {
        char[] arr = Integer.toString(n).toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
