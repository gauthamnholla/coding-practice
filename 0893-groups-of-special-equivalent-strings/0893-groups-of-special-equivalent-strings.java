class Solution {
    public int numSpecialEquivGroups(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            StringBuilder even = new StringBuilder();
            StringBuilder odd = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 0)
                    even.append(s.charAt(i));
                else
                    odd.append(s.charAt(i));
            }
            char[] evenArr = even.toString().toCharArray();
            char[] oddArr = odd.toString().toCharArray();
            Arrays.sort(evenArr);
            Arrays.sort(oddArr);
            String key = new String(evenArr) + new String(oddArr);
            map.merge(key, 1, Integer::sum);
        }
        return map.size();
    }
}