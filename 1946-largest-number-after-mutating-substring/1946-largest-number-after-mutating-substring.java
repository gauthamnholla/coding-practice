class Solution {
     public String maximumNumber(String num, int[] change) { 
        char[] chars = num.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            int currDigit = chars[i] - '0';
            if(currDigit < change[currDigit]) {
                while(i < chars.length && (chars[i] - '0' <= change[chars[i] - '0'])) {
                    chars[i] =  (char)(change[chars[i] - '0'] + '0');
                    i++;
                }
                break;
            }
        }
        return String.valueOf(chars);
    }
}