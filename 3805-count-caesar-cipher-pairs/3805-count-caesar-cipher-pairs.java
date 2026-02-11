import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countPairs(String[] words) {
        String[] a = words;
        Map<String, Integer> b = new HashMap<>();
        long c = 0;
        for (String d : a) {
            char[] e = d.toCharArray();
            int f = e[0];
            for (int g = 0; g < e.length; g++) {
                e[g] = (char) ((e[g] - f + 26) % 26);
            }
            String h = new String(e);
            int i = b.getOrDefault(h, 0);
            c += i;
            b.put(h, i + 1);
        }
        return c;
    }
}