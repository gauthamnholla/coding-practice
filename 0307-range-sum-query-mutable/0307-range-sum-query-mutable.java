class NumArray {

    private int[] fenwick;  // Fenwick Tree
    private int[] nums;     // store original array
    private int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        fenwick = new int[n + 1];

        // Build Fenwick Tree
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    // Add value to Fenwick tree at position index
    private void add(int index, int value) {
        while (index <= n) {
            fenwick[index] += value;
            index += index & -index;  // move to next
        }
    }

    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;
        add(index + 1, diff);  // update Fenwick tree
    }

    // Get prefix sum from 1 to index
    private int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += fenwick[index];
            index -= index & -index;  // move to parent
        }
        return sum;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}
