class Solution {
    public int minMovesToMakePalindrome(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        int moves = 0;

        while (left < right) {
            // If characters match, move inward
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                // Search for matching character from right
                int k = right;
                while (k > left && arr[k] != arr[left]) {
                    k--;
                }

                // If no matching character found, swap left character rightward
                if (k == left) {
                    swap(arr, left, left + 1);
                    moves++;
                } else {
                    // Bubble matching character to the right
                    while (k < right) {
                        swap(arr, k, k + 1);
                        moves++;
                        k++;
                    }
                    left++;
                    right--;
                }
            }
        }
        return moves;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
