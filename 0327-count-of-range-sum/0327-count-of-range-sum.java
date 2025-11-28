class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++)
            prefix[i + 1] = prefix[i] + nums[i];
        return mergeCount(prefix, 0, prefix.length - 1, lower, upper);
    }

    private int mergeCount(long[] arr, int left, int right, int lower, int upper) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        int count = mergeCount(arr, left, mid, lower, upper)
                  + mergeCount(arr, mid + 1, right, lower, upper);

        int i = mid + 1, j = mid + 1;
        for (int k = left; k <= mid; k++) {
            while (i <= right && arr[i] - arr[k] < lower) i++;
            while (j <= right && arr[j] - arr[k] <= upper) j++;
            count += j - i;
        }

        // merge step
        long[] temp = new long[right - left + 1];
        int p = left, q = mid + 1, t = 0;
        while (p <= mid && q <= right)
            temp[t++] = arr[p] < arr[q] ? arr[p++] : arr[q++];
        while (p <= mid) temp[t++] = arr[p++];
        while (q <= right) temp[t++] = arr[q++];
        System.arraycopy(temp, 0, arr, left, temp.length);

        return count;
    }
}
