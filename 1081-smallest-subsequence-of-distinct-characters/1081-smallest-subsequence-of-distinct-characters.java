class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];
        boolean[] inStack = new boolean[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        StringBuilder stack = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            freq[idx]--;
            
            if (inStack[idx]) continue; // already used this char
            
            while (stack.length() > 0) {
                char last = stack.charAt(stack.length() - 1);
                int lastIdx = last - 'a';
                if (last > c && freq[lastIdx] > 0) {
                    inStack[lastIdx] = false;
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }
            
            stack.append(c);
            inStack[idx] = true;
        }
        
        return stack.toString();
    }
}
