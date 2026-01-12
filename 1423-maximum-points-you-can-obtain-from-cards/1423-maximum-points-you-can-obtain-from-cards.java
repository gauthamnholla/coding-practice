class Solution {
    public int maxScore(int[] cardPoints, int k) {
         int lSum = 0;
        int rSum = 0;
        int maxSum = 0;

        for(int i = 0; i < k; i++){
            lSum += cardPoints[i];
        }

        maxSum = lSum;
        int rIndex = cardPoints.length - 1;

        for(int i = k-1; i >= 0 && rIndex > 0; i--){
            lSum = lSum - cardPoints[i];
            rSum = rSum + cardPoints[rIndex];
            rIndex--;
            int currSum = lSum + rSum;
            maxSum = Math.max(maxSum,currSum);
        }

        return maxSum;
    }
}