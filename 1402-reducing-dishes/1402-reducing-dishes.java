class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int total = 0, suffixSum = 0;

        for (int i = n - 1; i >= 0; i--) {
            suffixSum += satisfaction[i];
            if (suffixSum + total > total) {
                total += suffixSum;
            } else {
                break; 
            }
        }
        return total;
    }
}