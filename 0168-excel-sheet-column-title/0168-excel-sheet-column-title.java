class Solution {
    public String convertToTitle(int columnNumber) {
        char[] arr = new char[27];
        for (int i = 1; i <= 26; i++) {
            arr[i] = (char) ('A' + i - 1);
        }

        StringBuilder s = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            int rem = columnNumber % 26;
            s.append(arr[rem + 1]);
            columnNumber /= 26;
        }

        return s.reverse().toString();
    }
}