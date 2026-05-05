class Solution {
    public int maxGoodNumber(int[] nums) {
        int[][] combinations = {
            {nums[0], nums[1], nums[2]},
            {nums[0], nums[2], nums[1]},
            {nums[1], nums[0], nums[2]},
            {nums[1], nums[2], nums[0]},
            {nums[2], nums[0], nums[1]},
            {nums[2], nums[1], nums[0]},
        };

        long max = 0;
        
        for(int i = 0; i < 6; i++){
            long res = 0;
            for(int j = 0; j < 3; j++){
                int num = combinations[i][j];
                int digits = Integer.SIZE - Integer.numberOfLeadingZeros(num);

                res = (res << digits) + num;
            }
            max = Math.max(max, res);
        }
        return (int)(max);
    }
}