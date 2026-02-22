class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {

        int count = 0;
        for (int i = 0; i < arr.length - m; i++) {

            count = arr[i] == arr[i + m] ? ++count : 0;

            if (count >= m * (k - 1)) {
                return true;
            }
        }

        return false;
    }
}