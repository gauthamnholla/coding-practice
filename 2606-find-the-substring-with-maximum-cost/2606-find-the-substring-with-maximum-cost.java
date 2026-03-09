class Solution {
        public int maximumCostSubstring(String s, String chars, int[] vals) {
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            m.put(chars.charAt(i), vals[i]);
        }
        int res = 0, cur = 0;
        for (int i = 0; i < s.length(); ++i) {
            cur = Math.max(cur + m.getOrDefault(s.charAt(i), s.charAt(i) - 'a' + 1), 0);
            res = Math.max(res, cur);
        }
        return res;
    }
}