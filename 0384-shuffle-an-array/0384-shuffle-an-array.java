class Solution {

    int[] nums;
    int[] original;

    public Solution(int[] nums) {
        this.nums = nums;
        original = this.nums.clone();
    }
    
    public int[] reset() {
        this.nums = this.original.clone(); 
        return this.nums;
    }
    
    public int[] shuffle() {
        for (int i = this.nums.length - 1; i >= 1; i--) {
            int j = (int)Math.floor(Math.random() * (i + 1));
            int temp = this.nums[i];
            this.nums[i] = this.nums[j];
            this.nums[j] = temp;
        }
        return nums;
    }
}

