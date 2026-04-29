import java.util.*;

class Solution {
    private static final int MOD = 1000000007;
    
    // Helper function to calculate the combination formula (x * (x - 1)) >> 1
    public long f(int x) {
        return ((long)x * (x - 1)) >> 1;
    }
    
    public int subsequencesWithMiddleMode(int[] a) {
        int n = a.length;
        long ans = 0;
        
        // Frequency maps for left and right counts
        Map<Integer, Integer> ml = new HashMap<>();
        Map<Integer, Integer> mr = new HashMap<>();
        
        // Initialize the left and right frequency maps
        for (int j = 0; j < 1; j++) {
            ml.put(a[j], ml.getOrDefault(a[j], 0) + 1);
        }
        for (int j = 2; j < n; j++) {
            mr.put(a[j], mr.getOrDefault(a[j], 0) + 1);
        }
        
        // Iterate over each element in the array as the middle element
        for (int i = 2; i < n - 2; i++) {
            int c = a[i]; // center
            
            int cnt_l = 0;
            for (int j = 0; j < i; j++) {
                if (c == a[j]) {
                    cnt_l++;
                }
            }
            int cnt_r = 0;
            for (int j = i + 1; j < n; j++) {
                if (c == a[j]) {
                    cnt_r++;
                }
            }
            
            // Calculating possible ways to form subsequences
            long l0 = 0;
            if (i - cnt_l >= 2) l0 = f(i - cnt_l);
            
            long r0 = 0;
            if (n - i - 1 - cnt_r >= 2) r0 = f(n - i - 1 - cnt_r);
            
            long l1 = cnt_l * (i - cnt_l);
            long r1 = cnt_r * (n - i - 1 - cnt_r);
            
            long l2 = 0;
            if (cnt_l >= 2) l2 = f(cnt_l);
            
            long r2 = 0;
            if (cnt_r >= 2) r2 = f(cnt_r);
            
            // Update the result based on combinations of left and right subsequences
            ans = (ans + l0 * r2) % MOD;
            ans = (ans + l1 * r1) % MOD;
            ans = (ans + l1 * r2) % MOD;
            ans = (ans + l2 * r0) % MOD;
            ans = (ans + l2 * r1) % MOD;
            ans = (ans + l2 * r2) % MOD;
            
            // Update left and right frequency maps
            ml.put(a[i - 1], ml.getOrDefault(a[i - 1], 0) + 1);
            mr.put(c, mr.get(c) - 1);
            if (mr.get(c) == 0) {
                mr.remove(c);
            }
            
            // Calculate additional possible subsequences with updated frequency maps
            long ll = 0;
            for (int key : ml.keySet()) {
                if (key != c && ml.get(key) >= 2) {
                    ll += f(ml.get(key));
                }
            }
            long rr = 0;
            for (int key : mr.keySet()) {
                if (key != c && mr.get(key) >= 2) {
                    rr += f(mr.get(key));
                }
            }
            
            // Update the answer based on the current frequency maps
            for (int key : ml.keySet()) {
                if (key != c) {
                    if (!mr.containsKey(key)) {
                        ans += cnt_l * (f(n - i - 1 - cnt_r) - rr) * ml.get(key);
                    } else {
                        ans += cnt_l * (f(n - i - 1 - cnt_r - mr.get(key)) - rr + f(mr.get(key))) * ml.get(key);
                    }
                }
            }
            
            for (int key : mr.keySet()) {
                if (key != c) {
                    if (!ml.containsKey(key)) {
                        ans += cnt_r * (f(i - cnt_l) - ll) * mr.get(key);
                    } else {
                        ans += cnt_r * (f(i - cnt_l - ml.get(key)) - ll + f(ml.get(key))) * mr.get(key);
                    }
                }
            }

            // Ensure answer is within the MOD value
            ans %= MOD;
        }

        return (int) ans;
    }
}