class Solution {
    public String maximumOddBinaryNumber(String s) {
        int one = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                one++;
            }
        }
        int zero = s.length() - one;
        StringBuilder sb = new StringBuilder();
        sb.repeat('1', one - 1);
        sb.repeat('0', zero);
        sb.repeat("1", 1);
        return sb.toString();
    }
}