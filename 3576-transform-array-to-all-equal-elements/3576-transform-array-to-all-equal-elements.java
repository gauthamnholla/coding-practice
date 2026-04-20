class Solution {
    public boolean canMakeEqual(int[] nums, int k) {
        return check(nums, 1, k) || check(nums, -1, k);
    }

    private boolean check(int[] arr, int v, int k) {
        int count = 0;
        int curr = arr[0];
        
        for (int i = 0; i < arr.length - 1; i++) {
            if (curr != v) {
                curr = -arr[i + 1];
                count++;
            } else curr = arr[i + 1];
        }
        
        return curr == v && count <= k;
    }
}