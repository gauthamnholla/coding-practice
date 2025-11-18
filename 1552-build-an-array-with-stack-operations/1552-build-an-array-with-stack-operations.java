import java.util.*;

class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ops = new ArrayList<>();
        int cur = 1;      // current number from the stream
        int idx = 0;      // index in target
        
        while (idx < target.length) {
            if (cur == target[idx]) {
                ops.add("Push");
                idx++;
            } else {
                ops.add("Push");
                ops.add("Pop");
            }
            cur++;
        }
        
        return ops;
    }
}
