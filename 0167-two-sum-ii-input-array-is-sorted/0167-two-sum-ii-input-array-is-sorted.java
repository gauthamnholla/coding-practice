class Solution {
    public int[] twoSum(int[] numbers, int target) {

        int n = numbers.length;
        int i = 0;
        int j = n - 1;

        while (i < j) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                return new int[] { i + 1, j + 1 };
            }
        }

        return null;
    }
}