class Solution {

    public int titleToNumber(String columnTitle) {
        int col = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            col = col * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }

        return col;
    }
}