class Solution {
    public String reversePrefix(String s, int k) {
        char[] arr = s.toCharArray();
        int i = 0;
        k = k - 1;

        while (i < k) {
            char temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
            i++;
            k--;
        }

        return new String(arr);
    }
}