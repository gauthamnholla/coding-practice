class Solution {
    public int maxScore(int[] nums) {
        long sum = 0;
        int positives = 0, zeroes = 0, negatives = 0;

        for (int num : nums) {
            if (num > 0) {
                sum += num;
                positives++;
            } else if (num == 0) {
                zeroes++;
            } else {
                negatives++;
            }
        }

        if (positives <= 0) return 0;

        int[] negativeArr = new int[negatives];
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                negativeArr[k] = nums[i];
                k++;
            }
        }
        int score = zeroes + positives;
        Arrays.sort(negativeArr);

        for (int i = negativeArr.length - 1; i >= 0; i--) {
            sum += negativeArr[i];
            if (sum <= 0) break;
            score++;
        }
        return score;
    }
}