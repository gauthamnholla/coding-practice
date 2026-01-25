class Solution {
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> ch = new HashMap<>();

        for (char c : chars.toCharArray()) {
            ch.put(c, 1 + ch.getOrDefault(c, 0));
        }

        int res = 0;
        for (String word : words) {
            Map<Character, Integer> copy = new HashMap<>(ch);

            for (char c : word.toCharArray()) {
                if (copy.containsKey(c) && copy.get(c) != 0) {
                    copy.put(c, copy.get(c) - 1);
                } else {
                    res -= word.length();
                    break;
                }
            }

            res += word.length();
        }

        return res;        
    }
}