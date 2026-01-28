class Solution {
    public String reversePrefix(String word, char ch) {
        int charIndex = word.indexOf(ch);
        if(charIndex == -1) return word;

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i <= charIndex; i++) {
            stack.push(word.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= charIndex; i++) {
             sb.append(stack.pop());
        }

        for(int i = charIndex + 1; i < word.length(); i++) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}