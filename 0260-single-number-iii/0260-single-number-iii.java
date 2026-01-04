class Solution {
    public int[] singleNumber(int[] nums) {
        long XOR = 0;
        for(int num : nums) {
            XOR ^= num;
        }

        long rightmost = 0;
        rightmost = (XOR & XOR-1) ^ XOR;

        int b1 = 0, b2 = 0;
        for(int num : nums) {
            if((num & rightmost) == 0) {
                b1 ^= num;
            } else {
                b2 ^= num;
            }
        }

        return new int[] {b1, b2};
    }
} 