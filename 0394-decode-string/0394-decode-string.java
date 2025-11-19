import java.util.*;

class Solution {
    public String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> strings = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int num = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // build the repeat count (may be multi-digit)
                num = num * 10 + (ch - '0');
            } else if (ch == '[') {
                // push current state and reset for the inner string
                counts.push(num);
                strings.push(cur);
                num = 0;
                cur = new StringBuilder();
            } else if (ch == ']') {
                // finish current bracketed string, repeat and append to previous
                int repeat = counts.pop();
                StringBuilder prev = strings.pop();
                for (int i = 0; i < repeat; i++) prev.append(cur);
                cur = prev;
            } else {
                // letter
                cur.append(ch);
            }
        }

        return cur.toString();
    }
}
