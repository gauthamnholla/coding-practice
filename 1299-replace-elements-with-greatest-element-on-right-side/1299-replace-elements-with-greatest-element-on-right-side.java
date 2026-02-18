// Traverse from right and maintain running maximum
// Time - O(n)
// Space - O(1)
class Solution {
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int maxR = -1;

        for (int i = n - 1; i >= 0; i--) {
            int current = arr[i];
            arr[i] = maxR;
            maxR = Math.max(maxR, current);
        }

        return arr;
    }
}