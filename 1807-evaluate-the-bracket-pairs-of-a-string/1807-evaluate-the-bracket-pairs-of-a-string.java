import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> mp = new HashMap<>();
        for (List<String> pair : knowledge) {
            mp.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder ans = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                i++;
                StringBuilder key = new StringBuilder();
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }
                i++;
                ans.append(mp.getOrDefault(key.toString(), "?"));
            } else {
                ans.append(s.charAt(i));
                i++;
            }
        }
        return ans.toString();
    }
}