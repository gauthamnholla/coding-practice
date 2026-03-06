class Solution {
    public int similarPairs(String[] words) {
        Map<Set<Character>,Integer> map = new HashMap<>();
        for(String s : words)
        {
            Set<Character> hs = new HashSet<>();
            for(char ch : s.toCharArray())
                hs.add(ch);
            map.put(hs,map.getOrDefault(hs,0)+1);
        }
        int res = 0;
        for(int count : map.values())
        {
            res += (count*(count-1))/2;
        }
        return res;
    }
}