import java.util.*;

class Solution {
    public boolean isTransformable(String s, String t) {
        // queues for digits 0..9
        ArrayDeque<Integer>[] pos = new ArrayDeque[10];
        for (int i = 0; i < 10; i++) pos[i] = new ArrayDeque<>();

        // store indices of each digit in s
        for (int i = 0; i < s.length(); i++) {
            pos[s.charAt(i) - '0'].add(i);
        }

        // match characters of t
        for (char ch : t.toCharArray()) {
            int d = ch - '0';
            if (pos[d].isEmpty()) return false;

            int idx = pos[d].poll();  // earliest index where digit d occurs in s

            // check for locked smaller digits
            for (int smaller = 0; smaller < d; smaller++) {
                if (!pos[smaller].isEmpty() && pos[smaller].peek() < idx)
                    return false;
            }
        }
        return true;
    }
}
