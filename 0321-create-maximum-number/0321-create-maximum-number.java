class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] best = new int[k];

        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] a = maxSub(nums1, i);
            int[] b = maxSub(nums2, k - i);
            int[] merged = merge(a, b);
            if (greater(merged, 0, best, 0)) best = merged;
        }
        return best;
    }

    // pick largest subsequence of length k
    private int[] maxSub(int[] nums, int k) {
        int drop = nums.length - k;
        int[] stack = new int[k];
        int top = -1;

        for (int num : nums) {
            while (top >= 0 && stack[top] < num && drop > 0) {
                top--; drop--;
            }
            if (top + 1 < k) stack[++top] = num;
            else drop--;
        }
        return stack;
    }

    // merge two arrays into largest possible
    private int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] res = new int[a.length + b.length];

        while (i < a.length || j < b.length)
            res[k++] = greater(a, i, b, j) ? a[i++] : b[j++];

        return res;
    }

    // check if a[i:] > b[j:]
    private boolean greater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) { i++; j++; }
        if (j == b.length) return true;
        if (i == a.length) return false;
        return a[i] > b[j];
    }
}
